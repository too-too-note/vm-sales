/*
 * Auto-generated file. Created by MyBatis Generator
 * Generation date: 2021-06-09T20:53:50.103566+09:00
 */
package com.tkapp.cigarettesinventorycontrol.controller.salesinput

import com.tkapp.cigarettesinventorycontrol.model.data.VendingMachineRecord
import java.math.BigDecimal
import java.util.*
import javax.validation.constraints.NotNull

/**
 * 売り上げデータ検索フォーム
 */
data class SalesInputSelectForm(
        /**
         * 売り上げ日付
         */
        @field:NotNull
        var salesDate: Date? = null,

        /**
         * 自販機コード
         */
        @field:NotNull
        var vendingMachineId: Int? = null
)