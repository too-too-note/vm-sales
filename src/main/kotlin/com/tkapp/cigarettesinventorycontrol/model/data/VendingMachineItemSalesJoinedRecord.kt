/*
 * Auto-generated file. Created by MyBatis Generator
 * Generation date: 2021-06-12T00:24:10.604993+09:00
 */
package com.tkapp.cigarettesinventorycontrol.model.data

import java.math.BigDecimal
import java.util.Date

data class VendingMachineItemSalesJoinedRecord(
        var id: Int = 0,
        var salesQuantity: Int? = null,
        var salesDate: Date? = null,

        var itemId: Int? = null,
        var itemCode: String? = null,
        var itemName: String? = null,
        var price: BigDecimal? = null,
        var itemOrderSeq: Int? = null,

        var vendingMachineId: Int? = null,
        var vendingMachineName: String? = null,
        var vendingMachineCode: String? = null,
        var address: String? = null,
        var vendingMachineOrderSeq: Int? = null,
)