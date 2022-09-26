/*
 * Auto-generated file. Created by MyBatis Generator
 * Generation date: 2021-06-27T17:39:43.507894+09:00
 */
package com.tkapp.cigarettesinventorycontrol.mapper.data

import com.tkapp.cigarettesinventorycontrol.mapper.data.UserRoleDynamicSqlSupport.UserRole
import com.tkapp.cigarettesinventorycontrol.mapper.data.UserRoleDynamicSqlSupport.UserRole.roleId
import com.tkapp.cigarettesinventorycontrol.mapper.data.UserRoleDynamicSqlSupport.UserRole.userId
import com.tkapp.cigarettesinventorycontrol.model.data.UserRoleRecord
import org.mybatis.dynamic.sql.SqlBuilder.isEqualTo
import org.mybatis.dynamic.sql.util.kotlin.*
import org.mybatis.dynamic.sql.util.kotlin.mybatis3.*

fun UserRoleMapper.count(completer: CountCompleter) =
    countFrom(this::count, UserRole, completer)

fun UserRoleMapper.delete(completer: DeleteCompleter) =
    deleteFrom(this::delete, UserRole, completer)

fun UserRoleMapper.deleteByPrimaryKey(userId_: String, roleId_: String) =
    delete {
        where(userId, isEqualTo(userId_))
        and(roleId, isEqualTo(roleId_))
    }

fun UserRoleMapper.insert(record: UserRoleRecord) =
    insert(this::insert, record, UserRole) {
        map(userId).toProperty("userId")
        map(roleId).toProperty("roleId")
    }

fun UserRoleMapper.insertMultiple(records: Collection<UserRoleRecord>) =
    insertMultiple(this::insertMultiple, records, UserRole) {
        map(userId).toProperty("userId")
        map(roleId).toProperty("roleId")
    }

fun UserRoleMapper.insertMultiple(vararg records: UserRoleRecord) =
    insertMultiple(records.toList())

fun UserRoleMapper.insertSelective(record: UserRoleRecord) =
    insert(this::insert, record, UserRole) {
        map(userId).toPropertyWhenPresent("userId", record::userId)
        map(roleId).toPropertyWhenPresent("roleId", record::roleId)
    }

private val columnList = listOf(userId, roleId)

fun UserRoleMapper.selectOne(completer: SelectCompleter) =
    selectOne(this::selectOne, columnList, UserRole, completer)

fun UserRoleMapper.select(completer: SelectCompleter) =
    selectList(this::selectMany, columnList, UserRole, completer)

fun UserRoleMapper.selectDistinct(completer: SelectCompleter) =
    selectDistinct(this::selectMany, columnList, UserRole, completer)

fun UserRoleMapper.update(completer: UpdateCompleter) =
    update(this::update, UserRole, completer)

fun KotlinUpdateBuilder.updateAllColumns(record: UserRoleRecord) =
    apply {
        set(userId).equalTo(record::userId)
        set(roleId).equalTo(record::roleId)
    }

fun KotlinUpdateBuilder.updateSelectiveColumns(record: UserRoleRecord) =
    apply {
        set(userId).equalToWhenPresent(record::userId)
        set(roleId).equalToWhenPresent(record::roleId)
    }