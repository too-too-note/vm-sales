/*
 * Auto-generated file. Created by MyBatis Generator
 * Generation date: 2021-06-18T23:23:45.25306+09:00
 */
package com.tkapp.cigarettesinventorycontrol.model.data

import java.math.BigDecimal

data class VendingMachineRecord(
    var id: Int? = null,
    var vendingMachineName: String? = null,
    var address: String? = null,
    var vendingMachineCode: String? = null,
    var orderSeq: Int? = null,
    var feeRate: BigDecimal? = null,
    var feeFixed: BigDecimal? = null,
    var invalid: Boolean = false
)