/*
 * Auto-generated file. Created by MyBatis Generator
 * Generation date: 2021-06-18T23:23:45.250032+09:00
 */
package com.tkapp.cigarettesinventorycontrol.mapper.data

import java.math.BigDecimal
import java.sql.JDBCType
import java.util.Date
import org.mybatis.dynamic.sql.SqlTable

object TaxDynamicSqlSupport {
    object Tax : SqlTable("data.tax") {
        val id = column<Int>("id", JDBCType.INTEGER)

        val taxRate = column<BigDecimal>("tax_rate", JDBCType.NUMERIC)

        val date = column<Date>("date", JDBCType.DATE)
    }
}