/*
 * Auto-generated file. Created by MyBatis Generator
 * Generation date: 2021-06-18T23:23:45.253189+09:00
 */
package com.tkapp.cigarettesinventorycontrol.mapper.data

import java.math.BigDecimal
import java.sql.JDBCType
import org.mybatis.dynamic.sql.SqlTable

object VendingMachineDynamicSqlSupport {
    object VendingMachine : SqlTable("data.vending_machine") {
        val id = column<Int>("id", JDBCType.INTEGER)

        val vendingMachineName = column<String>("vending_machine_name", JDBCType.VARCHAR)

        val address = column<String>("address", JDBCType.VARCHAR)

        val vendingMachineCode = column<String>("vending_machine_code", JDBCType.VARCHAR)

        val invalid = column<Boolean>("invalid", JDBCType.BIT)

        val orderSeq = column<Int>("order_seq", JDBCType.INTEGER)

        val feeRate = column<BigDecimal>("fee_rate", JDBCType.NUMERIC)

        val feeFixed = column<BigDecimal>("fee_fixed", JDBCType.NUMERIC)
    }
}