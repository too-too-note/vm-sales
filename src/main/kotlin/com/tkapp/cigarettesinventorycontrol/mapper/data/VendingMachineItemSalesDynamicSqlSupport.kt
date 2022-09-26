/*
 * Auto-generated file. Created by MyBatis Generator
 * Generation date: 2021-06-18T23:23:45.254315+09:00
 */
package com.tkapp.cigarettesinventorycontrol.mapper.data

import java.sql.JDBCType
import java.util.Date
import org.mybatis.dynamic.sql.SqlTable

object VendingMachineItemSalesDynamicSqlSupport {
    object VendingMachineItemSales : SqlTable("data.vending_machine_item_sales") {
        val id = column<Int>("id", JDBCType.INTEGER)

        val salesQuantity = column<Int>("sales_quantity", JDBCType.INTEGER)

        val salesDate = column<Date>("sales_date", JDBCType.DATE)

        val vendingMachineId = column<Int>("vending_machine_id", JDBCType.INTEGER)

        val itemId = column<Int>("item_id", JDBCType.INTEGER)
    }
}