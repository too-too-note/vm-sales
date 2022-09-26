package com.tkapp.cigarettesinventorycontrol.service.salesinquiry.itemout

import java.math.BigDecimal

class ItemSalesByItemOut(
        var itemId: Int? = null,
        var itemCode: String? = null,
        var itemName: String? = null,
        var price: BigDecimal? = null,
        var orderSeq: Int? = null,
        var salesList: List<VmSalesByItemOut> = listOf(),

        ) {
    val subtotalQuantity: Int
        get() = salesList.sumOf { it.salesQuantity ?: 0 }

    val subtotalSales: BigDecimal
        get() = (price ?: BigDecimal.ZERO) * subtotalQuantity.toBigDecimal()
}