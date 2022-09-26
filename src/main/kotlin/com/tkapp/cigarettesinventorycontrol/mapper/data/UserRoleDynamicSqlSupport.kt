/*
 * Auto-generated file. Created by MyBatis Generator
 * Generation date: 2021-06-27T17:39:43.507612+09:00
 */
package com.tkapp.cigarettesinventorycontrol.mapper.data

import java.sql.JDBCType
import org.mybatis.dynamic.sql.SqlTable

object UserRoleDynamicSqlSupport {
    object UserRole : SqlTable("data.user_role") {
        val userId = column<String>("user_id", JDBCType.VARCHAR)

        val roleId = column<String>("role_id", JDBCType.VARCHAR)
    }
}