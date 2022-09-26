package com.tkapp.cigarettesinventorycontrol.service.salesupdate

import com.tkapp.cigarettesinventorycontrol.controller.salesupdate.SalesUpdateForm

interface SalesUpdateService {
    fun updateSales(form: SalesUpdateForm)
}