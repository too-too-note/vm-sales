package com.tkapp.cigarettesinventorycontrol.controller.salesdownload

import com.tkapp.cigarettesinventorycontrol.service.salesdownload.SalesDownloadService
import com.tkapp.cigarettesinventorycontrol.service.salesdownload.SalesDownloadServiceImpl
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping

/**
 * 売上データダウンロード
 */
@RestController
@RequestMapping("sales-download")
class SalesDownloadController(
        private val salesDownloadService: SalesDownloadService
) {

    /**
     * 売上データのExcelを作成して返す
     */
    @PostMapping("/download")
    fun selectItems(@RequestBody form: SalesDownloadForm): SalesDownloadOut {
        val binary = salesDownloadService.createSalesExcel(form)
        return SalesDownloadOut("sales_data.xlsx", binary)
    }
}