package com.tkapp.cigarettesinventorycontrol.service.salesdownload

import com.tkapp.cigarettesinventorycontrol.controller.salesdownload.SalesDownloadForm

interface SalesDownloadService {
    fun createSalesExcel(form: SalesDownloadForm): ByteArray
}