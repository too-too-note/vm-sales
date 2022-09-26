/*
 * Auto-generated file. Created by MyBatis Generator
 * Generation date: 2021-06-09T23:05:04.798581+09:00
 */
package com.tkapp.cigarettesinventorycontrol.service.salesinquiry.vmout

import java.math.BigDecimal

data class SalesInquiryVmOut(
        var totalSalesPriceActual: BigDecimal? = null,
        var totalSalesPriceOnDisplay: BigDecimal? = null,
        var vmSalesList: List<VmSalesByVmOut> = listOf(),
        )
{
    val totalSalesCalculated: BigDecimal
        get() = vmSalesList.map { it.subtotalSales }.sumOf { it }
}
