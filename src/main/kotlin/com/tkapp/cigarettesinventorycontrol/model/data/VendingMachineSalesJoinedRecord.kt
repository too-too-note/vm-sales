/*
 * Auto-generated file. Created by MyBatis Generator
 * Generation date: 2021-06-12T00:24:10.604993+09:00
 */
package com.tkapp.cigarettesinventorycontrol.model.data

import java.math.BigDecimal
import java.util.Date

data class VendingMachineSalesJoinedRecord(
        var id: Int? = null,
        var salesDate: Date? = null,
        var salesPriceOnDisplay: BigDecimal? = null,
        var salesPriceActual: BigDecimal? = null,

        var vendingMachineId: Int? = null,
        var vendingMachineName: String? = null,
        var vendingMachineCode: String? = null,
        var address: String? = null,
        var feeRate: BigDecimal? = null,
        var feeFixed: BigDecimal? = null,
        var vendingMachineOrderSeq: Int? = null,
)