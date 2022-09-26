package com.tkapp.cigarettesinventorycontrol.service.salesinquiry

import com.tkapp.cigarettesinventorycontrol.controller.salesinquiry.SalesInquirySelectForm
import com.tkapp.cigarettesinventorycontrol.service.salesinquiry.itemout.SalesInquiryItemOut
import com.tkapp.cigarettesinventorycontrol.service.salesinquiry.vmout.SalesInquiryVmOut

interface SalesInquiryService {
    fun selectItemSales(form: SalesInquirySelectForm): SalesInquiryItemOut
    fun selectVmSales(form: SalesInquirySelectForm): SalesInquiryVmOut
}