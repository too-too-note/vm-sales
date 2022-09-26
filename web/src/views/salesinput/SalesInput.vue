<template>
  <div>
    <v-row>
      <v-col :cols="4">
        <date-picker :key="dpId" v-model="inputForm.salesDate" @change="getVendingMachineItemSales" :label="'売上日付'"></date-picker>
      </v-col>

      <v-col cols="4">
        <v-autocomplete
            v-model="inputForm.vendingMachineId"
            @change="getVendingMachineItemSales"
            :items="vendingMachines"
            :item-text="item => item.vendingMachineCode + ' : ' + item.vendingMachineName"
            :item-value="item => item.id"
            chips
            hide-details
            small-chips
            label="対象自販機"
        ></v-autocomplete>
      </v-col>

      <v-col cols="4">
        <v-checkbox v-model="includeInvalid" label="無効レコードを含める" @change="select"></v-checkbox>
      </v-col>

      <v-col cols="12">
        <v-data-table
            :headers="headers"
            :items="dataList"
            class="elevation-0"
            :search="searchText"
            :items-per-page="-1"
            disable-sort
        >
          <template v-slot:top>
            <v-toolbar flat>
              <v-toolbar-title>売り上げ入力</v-toolbar-title>
              <v-spacer></v-spacer>
              <v-btn color="primary" @click="determine" :disabled="!inputForm.salesDate || !inputForm.vendingMachineId">売上確定</v-btn>
            </v-toolbar>
            <v-row style="margin-left: 5px">
              <v-col cols="3">
                <v-text-field
                    v-model="searchText"
                    append-icon="mdi-magnify"
                    label="検索ワード"
                    single-line
                ></v-text-field>
              </v-col>
              <v-col cols="3">
                <v-text-field
                    v-model="totalSales"
                    readonly
                    label="売上合計"
                    outlined
                    type="number">
                </v-text-field>
              </v-col>
              <v-col cols="3">
                <v-text-field
                    v-model="inputForm.salesPriceOnDisplay"
                    :disabled="!inputForm.salesDate || !inputForm.vendingMachineId"
                    label="売上(表示上)"
                    outlined
                    type="number">
                </v-text-field>
              </v-col>
              <v-col cols="3">
                <v-text-field
                    v-model="inputForm.salesPriceActual"
                    :disabled="!inputForm.salesDate || !inputForm.vendingMachineId"
                    label="売上(実際)"
                    outlined
                    type="number">
                </v-text-field>
              </v-col>
            </v-row>
          </template>
          <template v-slot:item.sales="{ item }">
            <div style="width: 120px;">
              <v-text-field
                  :disabled="!inputForm.salesDate || !inputForm.vendingMachineId"
                  v-model="salesMap[item['id']]"
                  dense
                  outlined
                  type="number"
                  hide-details>
              </v-text-field>
            </div>
          </template>
        </v-data-table>
      </v-col>
    </v-row>

    <v-row>
      <v-col>
        <v-btn color="error" @click="openConfirmDialog" :disabled="!inputForm.salesDate || !inputForm.vendingMachineId">売上削除</v-btn>
        <v-btn color="error" @click="dialog = true">売上データ振替</v-btn>
      </v-col>
    </v-row>

    <ConfirmDialog
        ref="confirm"
        title="確認"
        message="売上日付、対象自販機のデータを削除します、よろしいですか？"
        buttonMessage="確定"
        @confirm="deleteSales"
    >
    </ConfirmDialog>

    <v-row justify="center">
      <v-dialog
          v-model="dialog"
          width="100%"
          v-if="dialog"
      >
        <div>
          <v-card>
            <v-card-title>売り上げデータ振替</v-card-title>
            <v-card-text>
              <sales-data-trans
                  @reload="getVendingMachineItemSales"
              ></sales-data-trans>
            </v-card-text>
          </v-card>
        </div>
      </v-dialog>
    </v-row>
  </div>
</template>

<script>
import axios from "axios";
import ajax from "@/plugin/ajax"
import ConfirmDialog from "@/components/ConfirmDialog";
import DatePicker from "@/components/DatePicker";
import {generateMessage} from "@/plugin/utilities";
import SalesDataTrans from "@/views/salesinput/trans/SalesDataTrans";

export default {
  props: {
    argVendingMachineId: {},
    argSalesDate: {}
  },
  components: {
    ConfirmDialog, DatePicker, SalesDataTrans
  },
  name: "SalesInput.vue",
  data: () => ({
    inputForm: {
      salesDate: new Date().toISOString().substr(0, 10),
      vendingMachineId: null,
      salesPriceOnDisplay: null,
      salesPriceActual: null,
    },
    dataList: [],
    vendingMachines: [],
    headers: [
      { text: '商品コード', value: 'itemCode' },
      { text: '値段', value: 'price' },
      { text: '商品名', value: 'itemName', align: 'end' },
      { text: '売上数量', value: 'sales' },
    ],
    selectedItem: {},
    salesMap: {},
    beforeUpdateSalesMap: {},
    searchText: "",
    includeInvalid: false,
    dpId: 0,
    dialog: false
  }),
  async mounted() {
    await this.select()

    if (this.$props.argVendingMachineId && this.$props.argSalesDate) {
      this.inputForm.salesDate = this.$props.argSalesDate
      this.inputForm.vendingMachineId = this.$props.argVendingMachineId
      this.dpId++
      this.getVendingMachineItemSales()
    }
  },
  methods: {
    async select() {
      const form = { includeInvalid: this.includeInvalid }

      await Promise.all([
        ajax.post("sales/selectItems", form, (res) => {
          this.dataList = res.data
        }),
        ajax.post("sales/selectVendingMachines", form, (res) => {
          this.vendingMachines = res.data
        })
      ])
    },
    openConfirmDialog(item) {
      this.$refs.confirm.open()
    },
    determine() {
      const itemSalesList = []
      for (let itemId in this.salesMap) {
        let update = false
        const beforeUpdateSale = this.beforeUpdateSalesMap[itemId]
        if (beforeUpdateSale !== undefined) {
          if (this.salesMap[itemId] === beforeUpdateSale) {
            continue
          } else {
            update = true
          }
        }

        itemSalesList.push({
          itemId: itemId,
          salesQuantity: this.salesMap[itemId],
          update: update
        })
      }

      this.inputForm.itemSalesList = itemSalesList

      ajax.post("sales/determine", this.inputForm, (res) => {
        this.$emit("callBackUpdate")
        generateMessage("正常に計上されました", false)
        this.salesMap = {}
        this.getVendingMachineItemSales()
      })
    },
    getVendingMachineItemSales() {
      this.inputForm.salesPriceOnDisplay = null
      this.inputForm.salesPriceActual = null
      this.beforeUpdateSalesMap = {}
      this.salesMap = {}

      if (!this.inputForm.salesDate || !this.inputForm.vendingMachineId) return

      this.$store.state.targetDate = this.inputForm.salesDate
      this.$store.state.targetVM = this.vendingMachines.find(item => item.id === this.inputForm.vendingMachineId).vendingMachineName

      const form = {
        salesDate: this.inputForm.salesDate,
        vendingMachineId: this.inputForm.vendingMachineId,
      }
      ajax.post("sales/getVendingMachineItemSales", form, (res) => {
        this.beforeUpdateSalesMap = Object.assign({}, res.data['itemIdToSalesMap'])
        this.salesMap = res.data['itemIdToSalesMap']
        this.inputForm.salesPriceOnDisplay = res.data.salesPriceOnDisplay
        this.inputForm.salesPriceActual = res.data.salesPriceActual
      })
      this.dialog = false
    },

    deleteSales() {
      ajax.post("sales/deleteSales", this.inputForm, (res) => {
        generateMessage("正常に削除されました", false)
        this.salesMap = {}
        this.getVendingMachineItemSales()
      })
    }
  },
  computed: {
    totalSales() {
      let total = 0
      for (const id in this.salesMap) {
        const target = this.dataList.find(item => item.id === Number(id))
        if (!target) continue
        total += target['price'] * this.salesMap[id]
      }

      return total
    }
  },
  destroyed() {
    this.$store.state.targetVM = ""
    this.$store.state.targetDate = ""
  }
}
</script>


<style>
input[type="number"]::-webkit-outer-spin-button,
input[type="number"]::-webkit-inner-spin-button {
  -webkit-appearance: none;
  margin: 0;
}
</style>