<template>
  <div>
    <v-row>
      <v-col cols="12">
        <v-btn style="margin-left: 20px" @click="download">ダウンロード</v-btn>
      </v-col>
    </v-row>
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
    </v-row>
  </div>
</template>

<script>
import ajax from "@/plugin/ajax"
import DatePicker from "@/components/DatePicker";
import VmFeePrint from "@/views/vmfeeinquiry/print/VmFeePrint";
import {putOnComma} from "@/plugin/utilities";

export default {
  name: "SalesDownload.vue",
  components: { DatePicker },
  data: () => ({
    form: {
      fromDate: new Date().toISOString().substr(0, 10),
      toDate: new Date().toISOString().substr(0, 10),
      dateList: [],
      searchByDateList: false,
    },
    out: {},
  }),
  mounted() {
  },
  methods: {
    download() {
      this.printVmList = []
      ajax.post("sales-download/download", this.form, (res) => {
        this.out = res.data
        this.fileDownload(res.data.fileName, res.data.binary)
      })
    },

    fileDownload(fileName, binary) {
      const atob = window.atob(binary)
      const buf = new Uint8Array(atob.length)

      for (let i = 0; i < atob.length; i++) {
        buf[i] = atob.charCodeAt(i)
      }

      const blob = new Blob([buf.buffer])

      const aEl = document.createElement("a")
      aEl.download = fileName
      aEl.target = "_blank"

      aEl.href = window.webkitURL.createObjectURL(blob)
      aEl.click()
    }
  },
}
</script>


<style scoped>

</style>