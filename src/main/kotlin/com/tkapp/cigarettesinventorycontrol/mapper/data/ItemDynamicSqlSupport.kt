/*
 * Auto-generated file. Created by MyBatis Generator
 * Generation date: 2021-06-18T23:23:45.252126+09:00
 */
package com.tkapp.cigarettesinventorycontrol.mapper.data

import java.math.BigDecimal
import java.sql.JDBCType
import org.mybatis.dynamic.sql.SqlTable

object ItemDynamicSqlSupport {
    object Item : SqlTable("data.item") {
        val id = column<Int>("id", JDBCType.INTEGER)

        val itemCode = column<String>("item_code", JDBCType.VARCHAR)

        val itemName = column<String>("item_name", JDBCType.VARCHAR)

        val price = column<BigDecimal>("price", JDBCType.NUMERIC)

        val orderSeq = column<Int>("order_seq", JDBCType.INTEGER)

        val invalid = column<Boolean>("invalid", JDBCType.BIT)
    }
}