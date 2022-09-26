/*
 * Auto-generated file. Created by MyBatis Generator
 * Generation date: 2021-06-18T23:23:45.231832+09:00
 */
package com.tkapp.cigarettesinventorycontrol.mapper.data

import java.math.BigDecimal
import java.sql.JDBCType
import java.util.Date
import org.mybatis.dynamic.sql.SqlTable

object VendingMachineSalesDynamicSqlSupport {
    object VendingMachineSales : SqlTable("data.vending_machine_sales") {
        val id = column<Int>("id", JDBCType.INTEGER)

        val salesPriceOnDisplay = column<BigDecimal>("sales_price_on_display", JDBCType.NUMERIC)

        val salesPriceActual = column<BigDecimal>("sales_price_actual", JDBCType.NUMERIC)

        val salesDate = column<Date>("sales_date", JDBCType.DATE)

        val vendingMachineId = column<Int>("vending_machine_id", JDBCType.INTEGER)
    }
}