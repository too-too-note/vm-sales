/*
 * Auto-generated file. Created by MyBatis Generator
 * Generation date: 2021-06-27T17:39:43.508696+09:00
 */
package com.tkapp.cigarettesinventorycontrol.mapper.data

import com.tkapp.cigarettesinventorycontrol.mapper.data.RoleDynamicSqlSupport.Role
import com.tkapp.cigarettesinventorycontrol.mapper.data.RoleDynamicSqlSupport.Role.invalid
import com.tkapp.cigarettesinventorycontrol.mapper.data.RoleDynamicSqlSupport.Role.roleId
import com.tkapp.cigarettesinventorycontrol.mapper.data.RoleDynamicSqlSupport.Role.roleName
import com.tkapp.cigarettesinventorycontrol.model.data.RoleRecord
import org.mybatis.dynamic.sql.SqlBuilder.isEqualTo
import org.mybatis.dynamic.sql.util.kotlin.*
import org.mybatis.dynamic.sql.util.kotlin.mybatis3.*

fun RoleMapper.count(completer: CountCompleter) =
    countFrom(this::count, Role, completer)

fun RoleMapper.delete(completer: DeleteCompleter) =
    deleteFrom(this::delete, Role, completer)

fun RoleMapper.deleteByPrimaryKey(roleId_: String) =
    delete {
        where(roleId, isEqualTo(roleId_))
    }

fun RoleMapper.insert(record: RoleRecord) =
    insert(this::insert, record, Role) {
        map(roleId).toProperty("roleId")
        map(roleName).toProperty("roleName")
        map(invalid).toProperty("invalid")
    }

fun RoleMapper.insertMultiple(records: Collection<RoleRecord>) =
    insertMultiple(this::insertMultiple, records, Role) {
        map(roleId).toProperty("roleId")
        map(roleName).toProperty("roleName")
        map(invalid).toProperty("invalid")
    }

fun RoleMapper.insertMultiple(vararg records: RoleRecord) =
    insertMultiple(records.toList())

fun RoleMapper.insertSelective(record: RoleRecord) =
    insert(this::insert, record, Role) {
        map(roleId).toPropertyWhenPresent("roleId", record::roleId)
        map(roleName).toPropertyWhenPresent("roleName", record::roleName)
        map(invalid).toPropertyWhenPresent("invalid", record::invalid)
    }

private val columnList = listOf(roleId, roleName, invalid)

fun RoleMapper.selectOne(completer: SelectCompleter) =
    selectOne(this::selectOne, columnList, Role, completer)

fun RoleMapper.select(completer: SelectCompleter) =
    selectList(this::selectMany, columnList, Role, completer)

fun RoleMapper.selectDistinct(completer: SelectCompleter) =
    selectDistinct(this::selectMany, columnList, Role, completer)

fun RoleMapper.selectByPrimaryKey(roleId_: String) =
    selectOne {
        where(roleId, isEqualTo(roleId_))
    }

fun RoleMapper.update(completer: UpdateCompleter) =
    update(this::update, Role, completer)

fun KotlinUpdateBuilder.updateAllColumns(record: RoleRecord) =
    apply {
        set(roleId).equalTo(record::roleId)
        set(roleName).equalTo(record::roleName)
        set(invalid).equalTo(record::invalid)
    }

fun KotlinUpdateBuilder.updateSelectiveColumns(record: RoleRecord) =
    apply {
        set(roleId).equalToWhenPresent(record::roleId)
        set(roleName).equalToWhenPresent(record::roleName)
        set(invalid).equalToWhenPresent(record::invalid)
    }

fun RoleMapper.updateByPrimaryKey(record: RoleRecord) =
    update {
        set(roleName).equalTo(record::roleName)
        set(invalid).equalTo(record::invalid)
        where(roleId, isEqualTo(record::roleId))
    }

fun RoleMapper.updateByPrimaryKeySelective(record: RoleRecord) =
    update {
        set(roleName).equalToWhenPresent(record::roleName)
        set(invalid).equalToWhenPresent(record::invalid)
        where(roleId, isEqualTo(record::roleId))
    }