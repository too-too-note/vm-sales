import Vue from 'vue'
import App from './App.vue'
import router from './router'
import vuetify from './plugin/vuetify'
import { store } from './router/index'

Vue.config.productionTip = false

new Vue({
  vuetify,
  store,
  router,
  render: function (h) { return h(App) }
}).$mount('#app')
