package com.tkapp.cigarettesinventorycontrol.service.salesdatetrans

import com.tkapp.cigarettesinventorycontrol.controller.salesdatatrans.SalesDataTransForm

interface SalesDataTransService {
    /**
     * 売り上げデータを振り返る
     */
    fun translateSalesData(form: SalesDataTransForm)
}