/*
 * Auto-generated file. Created by MyBatis Generator
 * Generation date: 2021-06-09T20:53:50.103566+09:00
 */
package com.tkapp.cigarettesinventorycontrol.controller.salesinput

import java.math.BigDecimal
import java.util.*

/**
 * 売り上げ入力レスポンス
 */
data class SalesInputOut(
        /**
         * 売り上げ価格(自販機表示上)
         */
        var salesPriceActual: BigDecimal? = null,

        /**
         * 売り上げ価格(実際)
         */
        var salesPriceOnDisplay: BigDecimal? = null,

        /**
         * Map<自販機商品ID, 売り上げ個数>
         */
        var itemIdToSalesMap: Map<Int, Int> = mapOf(),
)