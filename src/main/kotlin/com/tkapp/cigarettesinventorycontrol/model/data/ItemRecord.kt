/*
 * Auto-generated file. Created by MyBatis Generator
 * Generation date: 2021-06-18T23:23:45.252002+09:00
 */
package com.tkapp.cigarettesinventorycontrol.model.data

import java.math.BigDecimal

data class ItemRecord(
    var id: Int? = null,
    var itemCode: String? = null,
    var itemName: String? = null,
    var price: BigDecimal? = null,
    var orderSeq: Int? = null,
    var invalid: Boolean = false
)