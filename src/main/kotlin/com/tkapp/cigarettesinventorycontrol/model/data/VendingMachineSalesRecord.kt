/*
 * Auto-generated file. Created by MyBatis Generator
 * Generation date: 2021-06-18T23:23:45.227473+09:00
 */
package com.tkapp.cigarettesinventorycontrol.model.data

import java.math.BigDecimal
import java.util.Date

data class VendingMachineSalesRecord(
    var id: Int? = null,
    var salesPriceOnDisplay: BigDecimal? = null,
    var salesPriceActual: BigDecimal? = null,
    var salesDate: Date? = null,
    var vendingMachineId: Int? = null
)