package com.tkapp.cigarettesinventorycontrol.service.salesinquiry.itemout

import java.util.*

class VmSalesByItemOut(
        var id: Int = 0,
        var vendingMachineId: Int? = null,
        var vendingMachineName: String? = null,
        var vendingMachineCode: String? = null,
        var salesQuantity: Int? = null,
        var salesDate: Date? = null,
)