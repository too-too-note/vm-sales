package com.tkapp.cigarettesinventorycontrol.service.salesinquiry.vmout

import java.math.BigDecimal
import java.util.*


class ItemSalesByVmOut(
        var id: Int = 0,
        var itemId: Int? = null,
        var itemCode: String? = null,
        var itemName: String? = null,
        var price: BigDecimal? = null,
        var salesQuantity: Int? = null,
        var salesDate: Date? = null,
        var orderSeq: Int? = null,
) {
    val amountOfSales: BigDecimal
        get() = (price ?: BigDecimal.ZERO) * (salesQuantity?.toBigDecimal() ?: BigDecimal.ZERO)
}