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
 * 売り上げデータ削除用フォーム
 */
data class SalesInputDeleteForm(
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
)