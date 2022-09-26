<template>
  <div>
    <div class="hide">
      <v-btn @click="close">戻る</v-btn>
      <v-btn v-print="'#print-content'">プリント</v-btn>
    </div>

    <div id="print-content">
      <div class="page-content" v-for="vmSalesInfo in printObject.salesList">
        <v-row>
          <v-col :cols="4">
            <img src="./logo.jpg" alt="logo" width="100px">
            <div>
              {{printObject.monthString}}月分
            </div>
          </v-col>
          <v-col :cols="4" align="center">
            自動販売機支払手数料明細書
          </v-col>
          <v-col :cols="4" align="right">
            <span>{{printObject.companyInfo['address']}}</span><br>
            <span>{{printObject.companyInfo['name']}}</span><br>
            <span>TEL  {{printObject.companyInfo['tel']}}</span><br>
            <span>FAX  {{printObject.companyInfo['fax']}}</span>
          </v-col>
        </v-row>
        <v-row justify="space-between">
          <v-col :cols="3">
            {{vmSalesInfo.vendingMachineName}} 御中
          </v-col>
        </v-row>
        <v-row justify="center" style="margin-bottom: 40px; margin-top: 40px">
          <v-col :cols="12" align="center">
            <table>
              <tr>
                <th>売上金額</th>
                <th>手数料</th>
                <th>支払額</th>
              </tr>
              <tr v-if="vmSalesInfo['feeRate'] !== null" style="text-align: right">
                <td>¥{{ putOnComma(vmSalesInfo['totalSalesPriceOnDisplay']) }}</td>
                <td>{{ putOnComma(vmSalesInfo['feeRate'] * 100) }}%</td>
                <td>¥{{ putOnComma(vmSalesInfo['calculatedFee']) }}</td>
              </tr>
              <tr v-else style="text-align: right">
                <td></td>
                <td></td>
                <td>¥{{ putOnComma(vmSalesInfo['feeFixed']) }}</td>
              </tr>
            </table>
          </v-col>
        </v-row>
        <v-row>
          <v-col :cols="12">
            毎度お引き立てを賜り有り難う存じます。<br>
            下記の通り販売手数料をお支払しますので、ご確認願います。
          </v-col>
        </v-row>
        <v-row justify="center" style="margin: 150px 0 100px 0;">
          <v-col :cols="12" align="center">
            <table>
              <tr>
                <th>支払額</th>
                <th>¥{{ putOnComma(vmSalesInfo['calculatedFee']) }}</th>
              </tr>
            </table>
            (消費税10％含）
          </v-col>
        </v-row>

        <div style="border-bottom: dotted 1px; margin: 150px 0 20px 0;"></div>

        <v-row justify="center">
          <v-col :cols="12" align="center">
            領収書
          </v-col>
        </v-row>

        <v-row justify="center">
          <v-col :cols="12" align="center">
            {{printObject.monthString}}月度売上手数料として下記金額領収致しました。
          </v-col>
        </v-row>

        <v-row>
          <v-col :cols="12" align="right">
            令和　年　月　日
          </v-col>
        </v-row>

        <v-row>
          <v-col :cols="12" style="text-decoration: underline">
            {{ printObject.companyInfo['name'] }}
          </v-col>
        </v-row>

        <v-row justify="center">
          <v-col :cols="1"></v-col>
          <v-col :cols="10" align="center">
            <table>
              <tr>
                <th style="text-decoration: underline">¥{{ putOnComma(vmSalesInfo['calculatedFee']) }}</th>
              </tr>
            </table>
          </v-col>
          <v-col :cols="1" align="center">
            印
          </v-col>
        </v-row>
      </div>
    </div>
  </div>
</template>

<script>
import ajax from "@/plugin/ajax"
import DatePicker from "@/components/DatePicker";
import { putOnComma } from "@/plugin/utilities";

export default {
  props: {
    printObject: {},
  },
  name: "VmFeePrint.vue",
  components: {DatePicker},
  data: () => ({
  }),
  mounted() {
    // window.print()
  },
  methods: {
    close() {
      this.$emit('close')
    },
    putOnComma(num) {
      return putOnComma(num)
    }
  },
  watch: {
  },
}
</script>


<style scoped>
  @page {
    size: A4;
    margin: 0;
  }

  .page-content {
    padding: 40px;
    page-break-after: always;
    position: relative;
  }

  table {
    border-collapse: separate;
    border-spacing: 10px;
  }


  @media screen {
    .page-content {
      box-shadow: 0 .5mm 2mm rgba(0, 0, 0, .3);
      margin-top: 5px;
      margin-bottom: 5px;
    }
  }

  * {
    font-size: 13px;
  }
</style>