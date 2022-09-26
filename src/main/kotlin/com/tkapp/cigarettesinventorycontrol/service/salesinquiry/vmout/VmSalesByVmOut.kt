package com.tkapp.cigarettesinventorycontrol.service.salesinquiry.vmout

import java.math.BigDecimal

class VmSalesByVmOut(
        var vendingMachineId: Int? = null,
        var vendingMachineName: String? = null,
        var vendingMachineCode: String? = null,
        var address: String? = null,
        var orderSeq: Int? = null,
        var subtotalSalesPriceOnDisplay: BigDecimal? = null,
        var subtotalSalesPriceActual: BigDecimal? = null,
        var itemList: List<ItemSalesByVmOut> = listOf(),
) {
    val subtotalSales: BigDecimal
        get() = itemList.sumOf { it.amountOfSales }
}