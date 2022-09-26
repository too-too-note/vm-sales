/*
 * Auto-generated file. Created by MyBatis Generator
 * Generation date: 2021-06-09T23:05:04.798581+09:00
 */
package com.tkapp.cigarettesinventorycontrol.service.salesinquiry.itemout

import java.math.BigDecimal

data class SalesInquiryItemOut(
        var totalSalesPriceActual: BigDecimal? = null,
        var totalSalesPriceOnDisplay: BigDecimal? = null,
        var itemSalesList: List<ItemSalesByItemOut> = listOf(),
) {
    val totalSalesCalculated: BigDecimal
        get() = itemSalesList.map { it.subtotalSales }.sumOf { it }
}