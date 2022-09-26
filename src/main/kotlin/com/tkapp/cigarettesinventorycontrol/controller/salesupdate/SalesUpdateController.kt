package com.tkapp.cigarettesinventorycontrol.controller.salesupdate

import com.tkapp.cigarettesinventorycontrol.controller.salesinquiry.SalesInquirySelectForm
import com.tkapp.cigarettesinventorycontrol.service.salesinquiry.SalesInquiryService
import com.tkapp.cigarettesinventorycontrol.service.salesinquiry.itemout.SalesInquiryItemOut
import com.tkapp.cigarettesinventorycontrol.service.salesinquiry.SalesInquiryServiceImpl
import com.tkapp.cigarettesinventorycontrol.service.salesinquiry.vmout.SalesInquiryVmOut
import com.tkapp.cigarettesinventorycontrol.service.salesupdate.SalesUpdateService
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping

@RestController
@RequestMapping("sales-update")
class SalesUpdateController(
        private val salesInquiryListService: SalesInquiryService,
        private val salesUpdateService: SalesUpdateService,
) {

    @PostMapping("/select-vm")
    fun selectVm(@RequestBody form: SalesInquirySelectForm): SalesInquiryVmOut {
        return salesInquiryListService.selectVmSales(form)
    }


    @PostMapping("/update-sales")
    fun updateSales(@RequestBody form: SalesUpdateForm) {
        salesUpdateService.updateSales(form)
    }
}