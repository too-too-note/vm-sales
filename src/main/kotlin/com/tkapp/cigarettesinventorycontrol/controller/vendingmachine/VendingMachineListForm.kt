/*
 * Auto-generated file. Created by MyBatis Generator
 * Generation date: 2021-06-08T19:39:22.525117+09:00
 */
package com.tkapp.cigarettesinventorycontrol.controller.vendingmachine

import com.tkapp.cigarettesinventorycontrol.common.validation.DeleteGroup
import com.tkapp.cigarettesinventorycontrol.common.validation.InsertGroup
import com.tkapp.cigarettesinventorycontrol.common.validation.UpdateGroup
import java.math.BigDecimal
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull

data class VendingMachineListForm(
        @field:NotNull(groups = [UpdateGroup::class, DeleteGroup::class])
        var id: Int? = null,

        @field:NotBlank(groups = [InsertGroup::class, UpdateGroup::class])
        var vendingMachineName: String? = null,

        @field:NotBlank(groups = [InsertGroup::class, UpdateGroup::class])
        var address: String? = null,

        @field:NotBlank(groups = [InsertGroup::class, UpdateGroup::class])
        var vendingMachineCode: String? = null,

        @field:NotNull(groups = [InsertGroup::class, UpdateGroup::class])
        var orderSeq: Int = 0,

        @field:NotNull(groups = [InsertGroup::class, UpdateGroup::class])
        var invalid: Boolean = false,

        var feeRate: BigDecimal? = null,
        var feeFixed: BigDecimal? = null,
)