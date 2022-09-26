/*
 * Auto-generated file. Created by MyBatis Generator
 * Generation date: 2021-06-09T20:53:50.103566+09:00
 */
package com.tkapp.cigarettesinventorycontrol.controller.salesdatatrans

import com.tkapp.cigarettesinventorycontrol.common.validation.DeleteGroup
import com.tkapp.cigarettesinventorycontrol.common.validation.InsertGroup
import com.tkapp.cigarettesinventorycontrol.common.validation.UpdateGroup
import java.math.BigDecimal
import java.util.*
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull

data class SalesDataTransForm(
        /**
         * 自販機ID
         */
        @field:NotNull
        var vendingMachineId: Int? = null,

        /**
         * from 日付
         */
        @field:NotNull
        var fromDate: Date? = null,

        /**
         * to 日付
         */
        @field:NotNull
        var toDate: Date? = null,
)