/*
 * Auto-generated file. Created by MyBatis Generator
 * Generation date: 2021-06-09T20:53:50.103566+09:00
 */
package com.tkapp.cigarettesinventorycontrol.controller.item

import com.tkapp.cigarettesinventorycontrol.common.validation.DeleteGroup
import com.tkapp.cigarettesinventorycontrol.common.validation.InsertGroup
import com.tkapp.cigarettesinventorycontrol.common.validation.UpdateGroup
import java.math.BigDecimal
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull

/**
 * 商品の入力フォーム
 */
data class ItemListForm(
        /**
         * 商品ID
         */
        @field:NotNull(groups = [UpdateGroup::class, DeleteGroup::class])
        var id: Int? = null,

        /**
         * 商品コード
         */
        @field:NotBlank(groups = [InsertGroup::class, UpdateGroup::class])
        var itemCode: String? = null,

        /**
         * 商品名
         */
        @field:NotBlank(groups = [InsertGroup::class, UpdateGroup::class])
        var itemName: String? = null,

        /**
         * 価格
         */
        @field:NotNull(groups = [InsertGroup::class, UpdateGroup::class])
        var price: BigDecimal? = null,

        /**
         * 並び順
         */
        @field:NotNull(groups = [InsertGroup::class, UpdateGroup::class])
        var orderSeq: Int? = null,

        /**
         * 無効
         */
        @field:NotNull(groups = [InsertGroup::class, UpdateGroup::class])
        var invalid: Boolean = false
)