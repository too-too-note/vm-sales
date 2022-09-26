import Vue from 'vue'
import VueRouter from 'vue-router'
import Index from "@/views/Index";
import ItemList from "@/views/item/ItemList";
import VendingMachineList from "@/views/vendingmachine/VendingMachineList";
import SalesInput from "@/views/salesinput/SalesInput";
import SalesInquiryItem from "@/views/salesinquiryitem/SalesInquiryItem";
import SalesInquiryVm from "@/views/salesinquiryvm/SalesInquiryVm";
import VmFeeInquiry from "@/views/vmfeeinquiry/VmFeeInquiry";
import SalesDownload from "@/views/salesdownload/SalesDownload";
import Print from 'vue-print-nb'
import Vuex from "vuex";
import SalesUpdate from "@/views/salesupdate/SalesUpdate";

Vue.use(Print)
Vue.use(VueRouter)
Vue.use(Vuex)

export const store = new Vuex.Store({
  state: {
    message: "",
    snackBar: false,
    color: "",
    targetDate: "",
    targetVM: "",
  },
  getters: {  },
  mutations: {  },
  actions: {  }
});

const routes = [
  {
    path: '/',
    name: 'Index',
    component: Index,
    children: [
      {
        name: "itemList",
        path: "item-list",
        component: ItemList
      },
      {
        name: "vendingMachineList",
        path: "vending-machine-list",
        component: VendingMachineList
      },
      {
        name: "salesInput",
        path: "sales-input",
        component: SalesInput
      },
      {
        name: "SalesUpdate",
        path: "sales-update",
        component: SalesUpdate
      },
      {
        name: "salesInquiryItem",
        path: "sales-inquiry-item",
        component: SalesInquiryItem
      },
      {
        name: "salesInquiryVm",
        path: "sales-inquiry-vm",
        component: SalesInquiryVm
      },
      {
        name: "vmFeeInquiry",
        path: "vm-fee-inquiry",
        component: VmFeeInquiry
      },
      {
        name: "salesDownload",
        path: "sales-download",
        component: SalesDownload
      }
    ]
  },
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
