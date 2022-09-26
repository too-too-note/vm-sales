<template>
  <div>
    <v-row style="margin-left: 5px; margin-bottom: 5px">
      <v-col :cols="3">
        <date-picker
            v-model="form.fromDate"
            :label="'from Date'"
        ></date-picker>
      </v-col>
      <v-col :cols="3">
        <date-picker
            v-model="form.toDate"
            :label="'to Date'"
        ></date-picker>
      </v-col>
      <v-col cols="4">
        <v-autocomplete
            v-model="form.vendingMachineId"
            :items="vendingMachines"
            :item-text="item => item.vendingMachineCode + ' : ' + item.vendingMachineName"
            :item-value="item => item.id"
            chips
            hide-details
            small-chips
            label="対象自販機"
        ></v-autocomplete>
      </v-col>
    </v-row>
    <v-row>
      <v-col align="left">
        <v-btn @click="openConfirmDialog" color="error" :disabled="!form.vendingMachineId">確定</v-btn>
      </v-col>
    </v-row>

    <ConfirmDialog
        ref="confirm"
        title="確認"
        :message="getVmName(form.vendingMachineId) + 'の' + form.fromDate + 'のデータを' + form.toDate + 'に振り替えます、よろしいですか？'"
        buttonMessage="確定"
        @confirm="update"
    >
    </ConfirmDialog>
  </div>
</template>

<script>
import ajax from "@/plugin/ajax"
import DatePicker from "@/components/DatePicker";
import {generateMessage, putOnComma} from "@/plugin/utilities";
import SalesInput from "@/views/salesinput/SalesInput";
import ConfirmDialog from "@/components/ConfirmDialog";

export default {
  name: "SalesDataTrans.vue",
  components: {DatePicker, SalesInput, ConfirmDialog},
  data: () => ({
    form: {
      fromDate: new Date().toISOString().substr(0, 10),
      toDate: new Date().toISOString().substr(0, 10),
      vendingMachineId: null,
    },
    vendingMachines: [],
    confirm: false
  }),
  mounted() {
    this.select()
  },
  methods: {
    async select() {
      const form = { includeInvalid: true }

      await ajax.post("sales/selectVendingMachines", form, (res) => {
        this.vendingMachines = res.data
      })
    },
    update() {
      ajax.post("sales-data-trans/execute", this.form, (res) => {
        generateMessage("正常に更新されました", false)
        this.$emit('reload')
        this.$emit('close')
      })
    },
    getVmName(id) {
      const vm = this.vendingMachines.find(vm => vm.id === id)
      return vm ? vm.vendingMachineName : ""
    },
    openConfirmDialog(item) {
      this.$refs.confirm.open()
    },
  },
}
</script>


<style scoped>

</style>