/*
 * Auto-generated file. Created by MyBatis Generator
 * Generation date: 2021-06-09T20:53:50.103566+09:00
 */
package com.tkapp.cigarettesinventorycontrol.controller.salesinput

import java.math.BigDecimal
import java.util.*
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull

/**
 * 売り上げデータ入力フォーム
 */
data class SalesInputForm(
        /**
         * 自販機ID
         */
        @field: NotNull
        var vendingMachineId: Int? = null,

        /**
         * 売り上げ日付
         */
        @field: NotNull
        var salesDate: Date? = null,

        /**
         * 売り上げ価格(自販機表示上)
         */
        @field: NotNull
        var salesPriceOnDisplay: BigDecimal? = null,

        /**
         * 売り上げ価格(実際)
         */
        @field: NotNull
        var salesPriceActual: BigDecimal? = null,

        /**
         * 商品売り上げリスト
         */
        var itemSalesList: List<ItemSales>
) {
    /**
     * 商品売り上げクラス
     */
    class ItemSales(
            /**
             * 商品ID
             */
            var itemId: Int = 0,

            /**
             * 売り上げ個数
             */
            var salesQuantity: Int? = null,

            /**
             * 更新フラグ
             */
            var update: Boolean = false
    )
}