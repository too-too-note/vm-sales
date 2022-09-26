<template>
  <div>
    <v-row>
      <v-col cols="12">
        <v-data-table
            item-key="vendingMachineId"
            :headers="headers"
            :items="out['vmSalesList']"
            class="elevation-0"
            show-expand
            :expanded.sync="expanded"
            :items-per-page="-1"
        >
          <template v-slot:top>
            <v-toolbar
                flat
            >
              <v-toolbar-title>売り上げ照会</v-toolbar-title>
              <v-btn style="margin-left: 20px" @click="allOpen">全て開く</v-btn>
              <v-btn @click="allClose">全て閉じる</v-btn>
              <v-radio-group
                  v-model="form.searchByDateList"
                  row
                  style="margin-top: 20px; margin-left: 20px"
              >
                <v-radio
                    label="日付範囲指定"
                    :value="false"
                ></v-radio>
                <v-radio
                    label="日付複数指定"
                    :value="true"
                ></v-radio>
              </v-radio-group>
              <v-spacer></v-spacer>
              <v-btn @click="select">検索</v-btn>
            </v-toolbar>
            <v-row style="margin-left: 5px; margin-bottom: 5px">
              <v-col :cols="3">
                <date-picker
                    v-model="form.fromDate"
                    :label="'from Date'"
                    :disabled="form.searchByDateList"
                ></date-picker>
              </v-col>
              <v-col :cols="3">
                <date-picker
                    v-model="form.toDate"
                    :label="'to Date'"
                    :disabled="form.searchByDateList"
                ></date-picker>
              </v-col>
              <v-col :cols="6">
                <date-picker
                    v-model="form.dateList"
                    :multiple="true"
                    :label="'Date List'"
                    :disabled="!form.searchByDateList"
                ></date-picker>
              </v-col>
              <v-col cols="2">
                <v-text-field v-model="form.itemCode" label="商品コード" hide-details></v-text-field>
              </v-col>
              <v-col cols="3">
                <v-text-field v-model="form.itemName" label="商品名"  hide-details></v-text-field>
              </v-col>
              <v-col cols="2">
                <v-text-field v-model="form.vendingMachineCode" label="自販機コード" hide-details></v-text-field>
              </v-col>
              <v-col cols="3">
                <v-text-field v-model="form.vendingMachineName" label="自販機名" hide-details></v-text-field>
              </v-col>
              <v-col cols="2">
                <v-text-field v-model="form.address" label="住所" hide-details></v-text-field>
              </v-col>
            </v-row>
          </template>
          <template v-slot:expanded-item="{ headers, item }">
            <td :colspan="headers.length">
              <v-data-table
                  item-key="id"
                  :headers="headers2"
                  :items="item['itemList']"
                  :items-per-page="-1"
                  hide-default-footer
              >
                <template v-slot:item.amountOfSales="{item}">
                  {{ putOnComma(item['amountOfSales']) }}
                </template>
              </v-data-table>
            </td>
          </template>
          <template v-slot:item.subtotalSalesPriceActual="{item}">
            {{ putOnComma(item['subtotalSalesPriceActual']) }}
          </template>
          <template v-slot:item.subtotalSalesPriceOnDisplay="{item}">
            {{ putOnComma(item['subtotalSalesPriceOnDisplay']) }}
          </template>
          <template v-slot:item.subtotalSales="{item}">
            {{ putOnComma(item['subtotalSales']) }}
          </template>
        </v-data-table>
      </v-col>
    </v-row>

    <v-row justify="end">
      <v-col :cols="3">
        <v-text-field readonly v-model="totalSalesPriceOnDisplay" label="表示上売上高合計"></v-text-field>
      </v-col>
      <v-col :cols="3">
        <v-text-field readonly v-model="totalSalesPriceActual" label="実際回収額合計"></v-text-field>
      </v-col>
      <v-col :cols="3">
        <v-text-field readonly v-model="totalSalesCalculated" label="計算上売上高合計"></v-text-field>
      </v-col>
    </v-row>
  </div>
</template>

<script>
import ajax from "@/plugin/ajax"
import DatePicker from "@/components/DatePicker";
import { putOnComma } from "@/plugin/utilities";

export default {
  name: "SalesInquiryVm.vue",
  components: {DatePicker},
  data: () => ({
    form: {
      fromDate: new Date().toISOString().substr(0, 10),
      toDate: new Date().toISOString().substr(0, 10),
      itemCode: "",
      itemName: "",
      vendingMachineCode: "",
      vendingMachineName: "",
      address: "",
      dateList: [],
      searchByDateList: false,
    },
    out: {},
    headers: [
      { text: '自販機名', value: 'vendingMachineName' },
      { text: '自販機コード', value: 'vendingMachineCode' },
      { text: '住所', value: 'address' },
      { text: '表示上売上高合計', value: 'subtotalSalesPriceOnDisplay', align: 'end' },
      { text: '実際回収額合計', value: 'subtotalSalesPriceActual', align: 'end' },
      { text: '売上高小計', value: 'subtotalSales', align: 'end' },
    ],
    headers2: [
      { text: '売上日付', value: 'salesDate' },
      { text: '商品名', value: 'itemName' },
      { text: '商品コード', value: 'itemCode' },
      { text: '価格', value: 'price', align: 'end' },
      { text: '売上数量', value: 'salesQuantity', align: 'end' },
      { text: '売上高', value: 'amountOfSales', align: 'end' },
    ],
    selectedItem: {},
    expanded: [],
    salesMap: {},
    searchText: "",
  }),
  mounted() {
    this.select()
  },
  methods: {
    select() {
      ajax.post("sales-inquiry-list/select-vm", this.form, (res) => {
        this.out = res.data
      })
    },
    allOpen() {
      this.expanded = []
      this.expanded = this.out['vmSalesList']
    },
    allClose() {
      this.expanded = []
    },
    putOnComma(num) {
      return putOnComma(num)
    }
  },
  computed: {
    totalSalesPriceActual() {
      return putOnComma(this.out['totalSalesPriceActual'])
    },
    totalSalesPriceOnDisplay() {
      return putOnComma(this.out['totalSalesPriceOnDisplay'])
    },
    totalSalesCalculated() {
      return putOnComma(this.out['totalSalesCalculated'])
    },
  },
  watch: {
    dialog (val) {
      val || this.close()
    },
    dialogDelete (val) {
      val || this.closeDelete()
    },
  },
}
</script>


<style scoped>

</style>