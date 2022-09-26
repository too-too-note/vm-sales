package com.tkapp.cigarettesinventorycontrol.controller.salesinquiry

import com.tkapp.cigarettesinventorycontrol.service.salesinquiry.SalesInquiryService
import com.tkapp.cigarettesinventorycontrol.service.salesinquiry.itemout.SalesInquiryItemOut
import com.tkapp.cigarettesinventorycontrol.service.salesinquiry.SalesInquiryServiceImpl
import com.tkapp.cigarettesinventorycontrol.service.salesinquiry.vmout.SalesInquiryVmOut
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping

/**
 * 売り上げ照会(商品、自販機で共用)
 */
@RestController
@RequestMapping("sales-inquiry-list")
class SalesInquiryController(
        private val salesInquiryListService: SalesInquiryService
) {

    /**
     * 商品基準で売り上げデータ検索
     */
    @PostMapping("/select-item")
    fun selectItem(@RequestBody form: SalesInquirySelectForm): SalesInquiryItemOut {
        return salesInquiryListService.selectItemSales(form)
    }

    /**
     * 自販機基準で売り上げデータ検索
     */
    @PostMapping("/select-vm")
    fun selectVm(@RequestBody form: SalesInquirySelectForm): SalesInquiryVmOut {
        return salesInquiryListService.selectVmSales(form)
    }
}