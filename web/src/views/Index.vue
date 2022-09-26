<template>
  <v-app>
    <v-navigation-drawer app width="300">
      <v-list-item>
        <v-list-item-content>
          <v-list-item-title class="title">
            <v-icon style="font-size: 28px">mdi-office-building</v-icon>
            ケイ・エンタープライズ
          </v-list-item-title>
        </v-list-item-content>
      </v-list-item>

      <v-divider></v-divider>

      <v-list dense nav>
        <v-list-item to="/">
          <v-list-item-action>
            <v-icon>mdi-home</v-icon>
          </v-list-item-action>
          <v-list-item-content>
            ホーム
          </v-list-item-content>
        </v-list-item>

        <v-list-item to="item-list">
          <v-list-item-action>
            <v-icon>mdi-table</v-icon>
          </v-list-item-action>
          <v-list-item-content>
            商品リスト
          </v-list-item-content>
        </v-list-item>

        <v-list-item to="vending-machine-list">
          <v-list-item-action>
            <v-icon>mdi-desktop-tower</v-icon>
          </v-list-item-action>
          <v-list-item-content>
            自販機リスト
          </v-list-item-content>
        </v-list-item>

        <v-list-item to="sales-input">
          <v-list-item-action>
            <v-icon>mdi-pencil-plus</v-icon>
          </v-list-item-action>
          <v-list-item-content>
            売り上げ入力
          </v-list-item-content>
        </v-list-item>

        <v-list-item to="sales-update">
          <v-list-item-action>
            <v-icon>mdi-pencil-plus</v-icon>
          </v-list-item-action>
          <v-list-item-content>
            売り上げ更新
          </v-list-item-content>
        </v-list-item>

        <v-list-item to="sales-inquiry-item">
          <v-list-item-action>
            <v-icon>mdi-database-search</v-icon>
          </v-list-item-action>
          <v-list-item-content>
            売り上げ照会(商品)
          </v-list-item-content>
        </v-list-item>

        <v-list-item to="sales-inquiry-vm">
          <v-list-item-action>
            <v-icon>mdi-database-search</v-icon>
          </v-list-item-action>
          <v-list-item-content>
            売り上げ照会(自販機)
          </v-list-item-content>
        </v-list-item>

        <v-list-item to="vm-fee-inquiry">
          <v-list-item-action>
            <v-icon>mdi-database-search</v-icon>
          </v-list-item-action>
          <v-list-item-content>
            自販機手数料照会
          </v-list-item-content>
        </v-list-item>

        <v-list-item to="sales-download">
          <v-list-item-action>
            <v-icon>mdi-download</v-icon>
          </v-list-item-action>
          <v-list-item-content>
            売上データダウンロード
          </v-list-item-content>
        </v-list-item>

      </v-list>
    </v-navigation-drawer>

    <v-app-bar color="primary" dark height="56" :absolute="$vuetify.breakpoint.xsOnly" app class="d-print-none">
      <div>自販機売上管理システム</div>
      <div style="margin-left: 50px">{{ $store.state.targetDate }}</div>
      <div style="margin-left: 50px">{{ $store.state.targetVM }}</div>
    </v-app-bar>

    <v-content>
      <v-container class="fill-height" style="max-width: 1500px">
        <v-row align="start" justify="start" class="fill-height">
          <v-col>
            <router-view></router-view>
          </v-col>
        </v-row>
      </v-container>
    </v-content>
  </v-app>
</template>

<script>
export default {
  name: "Index.vue"
}
</script>

<style scoped>

</style>