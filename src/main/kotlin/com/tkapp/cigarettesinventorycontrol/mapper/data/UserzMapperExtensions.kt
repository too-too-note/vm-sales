/*
 * Auto-generated file. Created by MyBatis Generator
 * Generation date: 2021-07-06T22:30:47.628716+09:00
 */
package com.tkapp.cigarettesinventorycontrol.mapper.data

import com.tkapp.cigarettesinventorycontrol.mapper.data.UserzDynamicSqlSupport.Userz
import com.tkapp.cigarettesinventorycontrol.mapper.data.UserzDynamicSqlSupport.Userz.invalid
import com.tkapp.cigarettesinventorycontrol.mapper.data.UserzDynamicSqlSupport.Userz.mailAddress
import com.tkapp.cigarettesinventorycontrol.mapper.data.UserzDynamicSqlSupport.Userz.password
import com.tkapp.cigarettesinventorycontrol.mapper.data.UserzDynamicSqlSupport.Userz.secret
import com.tkapp.cigarettesinventorycontrol.mapper.data.UserzDynamicSqlSupport.Userz.tel
import com.tkapp.cigarettesinventorycontrol.mapper.data.UserzDynamicSqlSupport.Userz.userId
import com.tkapp.cigarettesinventorycontrol.mapper.data.UserzDynamicSqlSupport.Userz.userName
import com.tkapp.cigarettesinventorycontrol.model.data.UserzRecord
import org.mybatis.dynamic.sql.SqlBuilder.isEqualTo
import org.mybatis.dynamic.sql.util.kotlin.*
import org.mybatis.dynamic.sql.util.kotlin.mybatis3.*

fun UserzMapper.count(completer: CountCompleter) =
    countFrom(this::count, Userz, completer)

fun UserzMapper.delete(completer: DeleteCompleter) =
    deleteFrom(this::delete, Userz, completer)

fun UserzMapper.deleteByPrimaryKey(userId_: String) =
    delete {
        where(userId, isEqualTo(userId_))
    }

fun UserzMapper.insert(record: UserzRecord) =
    insert(this::insert, record, Userz) {
        map(userId).toProperty("userId")
        map(userName).toProperty("userName")
        map(mailAddress).toProperty("mailAddress")
        map(tel).toProperty("tel")
        map(invalid).toProperty("invalid")
        map(password).toProperty("password")
        map(secret).toProperty("secret")
    }

fun UserzMapper.insertMultiple(records: Collection<UserzRecord>) =
    insertMultiple(this::insertMultiple, records, Userz) {
        map(userId).toProperty("userId")
        map(userName).toProperty("userName")
        map(mailAddress).toProperty("mailAddress")
        map(tel).toProperty("tel")
        map(invalid).toProperty("invalid")
        map(password).toProperty("password")
        map(secret).toProperty("secret")
    }

fun UserzMapper.insertMultiple(vararg records: UserzRecord) =
    insertMultiple(records.toList())

fun UserzMapper.insertSelective(record: UserzRecord) =
    insert(this::insert, record, Userz) {
        map(userId).toPropertyWhenPresent("userId", record::userId)
        map(userName).toPropertyWhenPresent("userName", record::userName)
        map(mailAddress).toPropertyWhenPresent("mailAddress", record::mailAddress)
        map(tel).toPropertyWhenPresent("tel", record::tel)
        map(invalid).toPropertyWhenPresent("invalid", record::invalid)
        map(password).toPropertyWhenPresent("password", record::password)
        map(secret).toPropertyWhenPresent("secret", record::secret)
    }

private val columnList = listOf(userId, userName, mailAddress, tel, invalid, password, secret)

fun UserzMapper.selectOne(completer: SelectCompleter) =
    selectOne(this::selectOne, columnList, Userz, completer)

fun UserzMapper.select(completer: SelectCompleter) =
    selectList(this::selectMany, columnList, Userz, completer)

fun UserzMapper.selectDistinct(completer: SelectCompleter) =
    selectDistinct(this::selectMany, columnList, Userz, completer)

fun UserzMapper.selectByPrimaryKey(userId_: String) =
    selectOne {
        where(userId, isEqualTo(userId_))
    }

fun UserzMapper.update(completer: UpdateCompleter) =
    update(this::update, Userz, completer)

fun KotlinUpdateBuilder.updateAllColumns(record: UserzRecord) =
    apply {
        set(userId).equalTo(record::userId)
        set(userName).equalTo(record::userName)
        set(mailAddress).equalTo(record::mailAddress)
        set(tel).equalTo(record::tel)
        set(invalid).equalTo(record::invalid)
        set(password).equalTo(record::password)
        set(secret).equalTo(record::secret)
    }

fun KotlinUpdateBuilder.updateSelectiveColumns(record: UserzRecord) =
    apply {
        set(userId).equalToWhenPresent(record::userId)
        set(userName).equalToWhenPresent(record::userName)
        set(mailAddress).equalToWhenPresent(record::mailAddress)
        set(tel).equalToWhenPresent(record::tel)
        set(invalid).equalToWhenPresent(record::invalid)
        set(password).equalToWhenPresent(record::password)
        set(secret).equalToWhenPresent(record::secret)
    }

fun UserzMapper.updateByPrimaryKey(record: UserzRecord) =
    update {
        set(userName).equalTo(record::userName)
        set(mailAddress).equalTo(record::mailAddress)
        set(tel).equalTo(record::tel)
        set(invalid).equalTo(record::invalid)
        set(password).equalTo(record::password)
        set(secret).equalTo(record::secret)
        where(userId, isEqualTo(record::userId))
    }

fun UserzMapper.updateByPrimaryKeySelective(record: UserzRecord) =
    update {
        set(userName).equalToWhenPresent(record::userName)
        set(mailAddress).equalToWhenPresent(record::mailAddress)
        set(tel).equalToWhenPresent(record::tel)
        set(invalid).equalToWhenPresent(record::invalid)
        set(password).equalToWhenPresent(record::password)
        set(secret).equalToWhenPresent(record::secret)
        where(userId, isEqualTo(record::userId))
    }