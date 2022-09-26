/*
 * Auto-generated file. Created by MyBatis Generator
 * Generation date: 2021-06-27T17:39:43.483283+09:00
 */
package com.tkapp.cigarettesinventorycontrol.mapper.data

import java.sql.JDBCType
import org.mybatis.dynamic.sql.SqlTable

object AddressBookDynamicSqlSupport {
    object AddressBook : SqlTable("data.address_book") {
        val id = column<String>("id", JDBCType.VARCHAR)

        val name = column<String>("name", JDBCType.VARCHAR)

        val address = column<String>("address", JDBCType.VARCHAR)

        val tel = column<String>("tel", JDBCType.VARCHAR)

        val fax = column<String>("fax", JDBCType.VARCHAR)

        val code = column<String>("code", JDBCType.VARCHAR)

        val content01 = column<String>("content01", JDBCType.VARCHAR)

        val content02 = column<String>("content02", JDBCType.VARCHAR)

        val content03 = column<String>("content03", JDBCType.VARCHAR)

        val content04 = column<String>("content04", JDBCType.VARCHAR)

        val content05 = column<String>("content05", JDBCType.VARCHAR)

        val invalid = column<Boolean>("invalid", JDBCType.BIT)
    }
}