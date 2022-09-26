<template>
  <div>
    <v-row>
      <v-col cols="12">
        <v-data-table
            :headers="headers"
            :items="filteredDataList"
            class="elevation-0"
            :search="searchText"
            :items-per-page="-1"
        >
          <template v-slot:top>
            <v-toolbar
                flat
            >
              <v-toolbar-title>商品リスト</v-toolbar-title>
              <v-spacer></v-spacer>
              <v-dialog
                  v-model="dialogEdit"
                  max-width="500px"
              >
                <template v-slot:activator="{ on, attrs }">
                  <v-btn
                      color="primary"
                      dark
                      class="mb-2"
                      v-bind="attrs"
                      v-on="on"
                  >
                    追加
                  </v-btn>
                </template>
                <v-card>
                  <v-card-title>
                    <span class="text-h5">商品</span>
                  </v-card-title>

                  <v-card-text>
                    <v-container>
                      <v-row>
                        <v-col
                            cols="12"
                            sm="6"
                            md="4"
                        >
                          <v-text-field
                              v-model="editedItem.itemName"
                              label="自販機名"
                          ></v-text-field>
                        </v-col>
                        <v-col
                            cols="12"
                            sm="6"
                            md="4"
                        >
                          <v-text-field
                              v-model="editedItem.itemCode"
                              label="自販機コード"
                          ></v-text-field>
                        </v-col>
                        <v-col
                            cols="12"
                            sm="6"
                            md="4"
                        >
                          <v-text-field
                              v-model="editedItem.price"
                              label="値段"
                          ></v-text-field>
                        </v-col>
                        <v-col
                            cols="12"
                            sm="6"
                            md="4"
                        >
                          <v-text-field
                              v-model="editedItem.orderSeq"
                              label="並び順"
                          ></v-text-field>
                        </v-col>
                        <v-col
                            cols="12"
                            sm="6"
                            md="4"
                        >
                          <v-checkbox
                              v-model="editedItem.invalid"
                              label="無効"
                          ></v-checkbox>
                        </v-col>
                      </v-row>
                    </v-container>
                  </v-card-text>

                  <v-card-actions>
                    <v-spacer></v-spacer>
                    <v-btn
                        color="blue darken-1"
                        text
                        @click="close"
                    >
                      Cancel
                    </v-btn>
                    <v-btn
                        color="blue darken-1"
                        text
                        @click="save"
                    >
                      Save
                    </v-btn>
                  </v-card-actions>
                </v-card>
              </v-dialog>
              <v-dialog v-model="dialogDelete" max-width="500px">
                <v-card>
                  <v-card-title class="text-h5">商品を削除します、よろしいですか?</v-card-title>
                  <v-card-actions>
                    <v-spacer></v-spacer>
                    <v-btn color="blue darken-1" text @click="closeDelete">Cancel</v-btn>
                    <v-btn color="blue darken-1" text @click="deleteItemConfirm">OK</v-btn>
                    <v-spacer></v-spacer>
                  </v-card-actions>
                </v-card>
              </v-dialog>
            </v-toolbar>
            <v-row>
              <v-col cols="3">
                <v-text-field
                    v-model="searchText"
                    append-icon="mdi-magnify"
                    label="検索ワード"
                    single-line
                ></v-text-field>
              </v-col>
              <v-col cols="3">
                <v-checkbox
                    label="無効レコードを表示"
                    v-model="displayInvalid"
                ></v-checkbox>
              </v-col>
            </v-row>
          </template>

          <template v-slot:item.actions="{ item }">
            <v-icon class="mr-2" @click="editItem(item)">
              mdi-pencil
            </v-icon>
            <v-icon @click="deleteItem(item)" v-if="!displayInvalid">
              mdi-delete
            </v-icon>
          </template>
        </v-data-table>
      </v-col>
    </v-row>
  </div>
</template>

<script>
import ajax from "@/plugin/ajax"
import {generateMessage} from "@/plugin/utilities";

export default {
  name: "ItemList.vue",
  data: () => ({
    form: {
      itemCode: "",
      itemName: "",
    },
    dataList: [],
    headers: [
      { text: '商品名', value: 'itemName' },
      { text: '商品コード', value: 'itemCode'},
      { text: '値段', value: 'price', align: 'end' },
      { text: '並び順', value: 'orderSeq', align: 'end' },
      { text: '無効', value: 'invalid' },
      { text: '', value: 'actions', sortable: false },
    ],
    dialogEdit: false,
    dialogDelete: false,
    editedIndex: -1,
    editedItem: {
      itemName: '',
      itemCode: '',
      price: 0,
      orderSeq: 0,
      invalid: false
    },
    defaultItem: {
      itemName: '',
      itemCode: '',
      price: 0,
      orderSeq: 0,
      invalid: false
    },
    selectedItem: {},
    selectedItemByDelete: {},
    searchText: "",
    displayInvalid: false,
  }),
  mounted() {
    this.select()
  },
  methods: {
    select() {
      ajax.post("item-list/select", this.form, (res) => {
        this.dataList = res.data
      })
    },
    editItem (item) {
      this.editedIndex = this.dataList.indexOf(item)
      this.editedItem = Object.assign({}, item)
      this.dialogEdit = true
    },

    deleteItem (item) {
      this.selectedItemByDelete = item
      this.editedItem = Object.assign({}, item)
      this.dialogDelete = true
    },

    async deleteItemConfirm () {
      await ajax.post("item-list/delete", this.selectedItemByDelete)

      generateMessage("正常に削除されました")

      this.closeDelete()
      await this.select()
    },

    close () {
      this.dialogEdit = false
      this.$nextTick(() => {
        this.editedItem = Object.assign({}, this.defaultItem)
        this.editedIndex = -1
      })
    },

    closeDelete () {
      this.selectedItemByDelete = {}
      this.dialogDelete = false
      this.$nextTick(() => {
        this.editedItem = Object.assign({}, this.defaultItem)
        this.editedIndex = -1
      })
    },

    async save () {
      const url = this.editedIndex > -1 ? "item-list/update" : "item-list/insert"

      const res = await ajax.post(url, this.editedItem)
      if (!res) return

      generateMessage("正常に処理されました")

      this.close()
      await this.select()
    },
  },
  computed: {
    filteredDataList() {
      return this.dataList.filter((data) => data.invalid === this.displayInvalid)
    }
  },
  watch: {
    dialogEdit (val) {
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