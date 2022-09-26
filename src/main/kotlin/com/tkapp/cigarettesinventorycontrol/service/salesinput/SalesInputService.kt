package com.tkapp.cigarettesinventorycontrol.service.salesinput

import com.tkapp.cigarettesinventorycontrol.controller.salesinput.SalesInputDeleteForm
import com.tkapp.cigarettesinventorycontrol.controller.salesinput.SalesInputForm
import com.tkapp.cigarettesinventorycontrol.controller.salesinput.SalesInputSelectForm
import com.tkapp.cigarettesinventorycontrol.model.data.VendingMachineSalesRecord

interface SalesInputService {
    fun determine(form: SalesInputForm)
    fun getMapVendingMachineSalesIdToSales(form: SalesInputSelectForm): Map<Int, Int>
    fun getVendingMachineSales(form: SalesInputSelectForm): VendingMachineSalesRecord?
    fun deleteSales(form: SalesInputDeleteForm)
}