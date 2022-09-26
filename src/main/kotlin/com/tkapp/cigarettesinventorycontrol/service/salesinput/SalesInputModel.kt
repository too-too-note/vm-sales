package com.tkapp.cigarettesinventorycontrol.service.salesinput

import java.math.BigDecimal

data class SalesInputModel(
        var id: Int? = null,
        var itemCode: String? = null,
        var itemName: String? = null,
        var price: BigDecimal? = null,
        var orderSeq: Int? = null,
)