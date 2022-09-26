<template>
  <div>
    <div v-show="!showPrint">
      <v-row>
        <v-col cols="12">
          <v-data-table
              item-key="vendingMachineId"
              :headers="headers"
              :items="out['vendingMachineList']"
              class="elevation-0"
              show-expand
              :expanded.sync="expanded"
              :items-per-page="-1"
          >
            <template v-slot:top>
              <v-toolbar
                  flat
              >
                <v-toolbar-title>自販機手数料照会</v-toolbar-title>
                <v-btn style="margin-left: 20px" @click="allOpen">全て開く</v-btn>
                <v-btn @click="allClose">全て閉じる</v-btn>
                <v-btn @click="checkSelectAll">全選択</v-btn>
                <v-btn @click="checkDeselectAll">全選択解除</v-btn>
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
                <v-btn @click="openPrint" :disabled="!printVmList.length">印刷</v-btn>
                <v-btn @click="select" :disabled="disabledSearchButton">検索</v-btn>
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
                    :headers="headers2"
                    :items="item['salesList']"
                    :items-per-page="-1"
                    hide-default-footer
                >
                  <template v-slot:item.salesPriceOnDisplay="{ item }">
                    {{ putOnComma(item['salesPriceOnDisplay']) }}
                  </template>
                  <template v-slot:item.salesPriceActual="{ item }">
                    {{ putOnComma(item['salesPriceActual']) }}
                  </template>
                </v-data-table>
              </td>
            </template>
            <template v-slot:item.feeFixed="{ item }">
              {{ item['feeFixed'] === null ? null : putOnComma(item['feeFixed']) }}
            </template>
            <template v-slot:item.totalSalesPriceOnDisplay="{ item }">
              {{ putOnComma(item['totalSalesPriceOnDisplay']) }}
            </template>
            <template v-slot:item.action="{ item }">
              <v-checkbox v-model="printVmList" :value="item" multiple append-icon="mdi-printer"></v-checkbox>
            </template>
          </v-data-table>
        </v-col>
      </v-row>

      <v-row justify="end">
        <v-col :cols="3">
          <v-text-field readonly v-model="totalFee" label="合計手数料"></v-text-field>
        </v-col>
      </v-row>
    </div>

    <div v-if="showPrint">
      <vm-fee-print @close="showPrint = false" :print-object="printObject"></vm-fee-print>
    </div>
  </div>
</template>

<script>
import ajax from "@/plugin/ajax"
import DatePicker from "@/components/DatePicker";
import VmFeePrint from "@/views/vmfeeinquiry/print/VmFeePrint";
import {putOnComma} from "@/plugin/utilities";

export default {
  name: "VmFeeInquiry.vue",
  components: { DatePicker, VmFeePrint },
  data: () => ({
    form: {
      fromDate: new Date().toISOString().substr(0, 10),
      toDate: new Date().toISOString().substr(0, 10),
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
      { text: '手数料(率)', value: 'feeRate', align: 'end' },
      { text: '手数料(固定値)', value: 'feeFixed', align: 'end' },
      { text: '合計表示上売上高', value: 'totalSalesPriceOnDisplay', align: 'end' },
      { text: '計算後手数料', value: 'calculatedFee', align: 'end' },
      { text: '', value: 'action' },
    ],
    headers2: [
      { text: '日付', value: 'salesDate' },
      { text: '表示上売上高', value: 'salesPriceOnDisplay', align: 'end' },
      { text: '実際回収額', value: 'salesPriceActual', align: 'end' },
    ],
    selectedItem: {},
    expanded: [],
    salesMap: {},
    showPrint: false,
    vmSalesInfoForPrint: {},
    printVmList: [],
    printObject: {
      salesList: [],
      monthString: "",
      companyInfo: {}
    },
    searchText: ""
  }),
  mounted() {
    this.select()
  },
  methods: {
    select() {
      this.printVmList = []
      ajax.post("vm-fee-inquiry/select", this.form, (res) => {
        this.out = res.data
      })
    },
    allOpen() {
      this.expanded = []
      this.expanded = this.out['vendingMachineList']
    },
    allClose() {
      this.expanded = []
    },
    openPrint() {
      let month = ""
      if (this.form.searchByDateList) {
        const aryMin = function (a, b) {
          return Math.min(a, b)
        }
        const months = this.form.dateList.map((i) => Number(i.split("-")[1]))
        month = months.reduce(aryMin)
      } else {
        month = Number(this.form.fromDate.split("-")[1]).toString()
      }

      this.printObject = {
        salesList: this.printVmList,
        monthString: month,
        companyInfo: this.out.companyInfo
      }
      this.showPrint = true
    },
    putOnComma(num) {
      return putOnComma(num)
    },
    checkSelectAll() {
      this.printVmList = this.out['vendingMachineList']
    },
    checkDeselectAll() {
      this.printVmList = []
    },
  },
  computed: {
    totalFee() {
      return putOnComma(this.out.totalFee)
    },
    disabledSearchButton() {
      if (this.form.searchByDateList) {
        return this.form.dateList.length === 0
      }
      return false
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