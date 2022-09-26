/*
 * Auto-generated file. Created by MyBatis Generator
 * Generation date: 2021-07-06T22:30:47.627987+09:00
 */
package com.tkapp.cigarettesinventorycontrol.mapper.data

import java.sql.JDBCType
import org.mybatis.dynamic.sql.SqlTable

object UserzDynamicSqlSupport {
    object Userz : SqlTable("data.userz") {
        val userId = column<String>("user_id", JDBCType.VARCHAR)

        val userName = column<String>("user_name", JDBCType.VARCHAR)

        val mailAddress = column<String>("mail_address", JDBCType.VARCHAR)

        val tel = column<String>("tel", JDBCType.VARCHAR)

        val invalid = column<Boolean>("invalid", JDBCType.BIT)

        val password = column<String>("password", JDBCType.VARCHAR)

        val secret = column<String>("secret", JDBCType.VARCHAR)
    }
}