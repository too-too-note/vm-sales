/*
 * Auto-generated file. Created by MyBatis Generator
 * Generation date: 2021-06-09T20:53:50.103566+09:00
 */
package com.tkapp.cigarettesinventorycontrol.controller.salesupdate

import java.util.*
import javax.validation.constraints.NotNull

data class SalesUpdateForm(
        var vmItemSalesList: List<VmItemSales> = listOf()
)

class VmItemSales(
        var vendingMachineItemSalesId: Int = 0,

        var salesQuantity: Int? = null,
)