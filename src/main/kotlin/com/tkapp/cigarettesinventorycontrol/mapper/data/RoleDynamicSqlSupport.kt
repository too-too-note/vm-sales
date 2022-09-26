/*
 * Auto-generated file. Created by MyBatis Generator
 * Generation date: 2021-06-27T17:39:43.508385+09:00
 */
package com.tkapp.cigarettesinventorycontrol.mapper.data

import java.sql.JDBCType
import org.mybatis.dynamic.sql.SqlTable

object RoleDynamicSqlSupport {
    object Role : SqlTable("data.role") {
        val roleId = column<String>("role_id", JDBCType.VARCHAR)

        val roleName = column<String>("role_name", JDBCType.VARCHAR)

        val invalid = column<Boolean>("invalid", JDBCType.BIT)
    }
}