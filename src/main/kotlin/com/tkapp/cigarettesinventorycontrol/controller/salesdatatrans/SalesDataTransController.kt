package com.tkapp.cigarettesinventorycontrol.controller.salesdatatrans

import com.tkapp.cigarettesinventorycontrol.service.salesdatetrans.SalesDataTransService
import com.tkapp.cigarettesinventorycontrol.service.salesdownload.SalesDownloadService
import com.tkapp.cigarettesinventorycontrol.service.salesdownload.SalesDownloadServiceImpl
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping

/**
 * 売上データ振替ダイアログ
 */
@RestController
@RequestMapping("sales-data-trans")
class SalesDataTransController(
        private val salesDataTransService: SalesDataTransService,
) {
    /**
     * 対象の自販機、日付の売上を別の日付に振り返る(間違えた時用にいるらしい)
     */
    @PostMapping("/execute")
    fun execute(@RequestBody form: SalesDataTransForm) {
        salesDataTransService.translateSalesData(form)
    }
}