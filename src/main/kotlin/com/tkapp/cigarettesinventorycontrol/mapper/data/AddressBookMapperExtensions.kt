/*
 * Auto-generated file. Created by MyBatis Generator
 * Generation date: 2021-06-27T17:39:43.492315+09:00
 */
package com.tkapp.cigarettesinventorycontrol.mapper.data

import com.tkapp.cigarettesinventorycontrol.mapper.data.AddressBookDynamicSqlSupport.AddressBook
import com.tkapp.cigarettesinventorycontrol.mapper.data.AddressBookDynamicSqlSupport.AddressBook.address
import com.tkapp.cigarettesinventorycontrol.mapper.data.AddressBookDynamicSqlSupport.AddressBook.code
import com.tkapp.cigarettesinventorycontrol.mapper.data.AddressBookDynamicSqlSupport.AddressBook.content01
import com.tkapp.cigarettesinventorycontrol.mapper.data.AddressBookDynamicSqlSupport.AddressBook.content02
import com.tkapp.cigarettesinventorycontrol.mapper.data.AddressBookDynamicSqlSupport.AddressBook.content03
import com.tkapp.cigarettesinventorycontrol.mapper.data.AddressBookDynamicSqlSupport.AddressBook.content04
import com.tkapp.cigarettesinventorycontrol.mapper.data.AddressBookDynamicSqlSupport.AddressBook.content05
import com.tkapp.cigarettesinventorycontrol.mapper.data.AddressBookDynamicSqlSupport.AddressBook.fax
import com.tkapp.cigarettesinventorycontrol.mapper.data.AddressBookDynamicSqlSupport.AddressBook.id
import com.tkapp.cigarettesinventorycontrol.mapper.data.AddressBookDynamicSqlSupport.AddressBook.invalid
import com.tkapp.cigarettesinventorycontrol.mapper.data.AddressBookDynamicSqlSupport.AddressBook.name
import com.tkapp.cigarettesinventorycontrol.mapper.data.AddressBookDynamicSqlSupport.AddressBook.tel
import com.tkapp.cigarettesinventorycontrol.model.data.AddressBookRecord
import org.mybatis.dynamic.sql.SqlBuilder.isEqualTo
import org.mybatis.dynamic.sql.util.kotlin.*
import org.mybatis.dynamic.sql.util.kotlin.mybatis3.*

fun AddressBookMapper.count(completer: CountCompleter) =
    countFrom(this::count, AddressBook, completer)

fun AddressBookMapper.delete(completer: DeleteCompleter) =
    deleteFrom(this::delete, AddressBook, completer)

fun AddressBookMapper.deleteByPrimaryKey(id_: String) =
    delete {
        where(id, isEqualTo(id_))
    }

fun AddressBookMapper.insert(record: AddressBookRecord) =
    insert(this::insert, record, AddressBook) {
        map(id).toProperty("id")
        map(name).toProperty("name")
        map(address).toProperty("address")
        map(tel).toProperty("tel")
        map(fax).toProperty("fax")
        map(code).toProperty("code")
        map(content01).toProperty("content01")
        map(content02).toProperty("content02")
        map(content03).toProperty("content03")
        map(content04).toProperty("content04")
        map(content05).toProperty("content05")
        map(invalid).toProperty("invalid")
    }

fun AddressBookMapper.insertMultiple(records: Collection<AddressBookRecord>) =
    insertMultiple(this::insertMultiple, records, AddressBook) {
        map(id).toProperty("id")
        map(name).toProperty("name")
        map(address).toProperty("address")
        map(tel).toProperty("tel")
        map(fax).toProperty("fax")
        map(code).toProperty("code")
        map(content01).toProperty("content01")
        map(content02).toProperty("content02")
        map(content03).toProperty("content03")
        map(content04).toProperty("content04")
        map(content05).toProperty("content05")
        map(invalid).toProperty("invalid")
    }

fun AddressBookMapper.insertMultiple(vararg records: AddressBookRecord) =
    insertMultiple(records.toList())

fun AddressBookMapper.insertSelective(record: AddressBookRecord) =
    insert(this::insert, record, AddressBook) {
        map(id).toPropertyWhenPresent("id", record::id)
        map(name).toPropertyWhenPresent("name", record::name)
        map(address).toPropertyWhenPresent("address", record::address)
        map(tel).toPropertyWhenPresent("tel", record::tel)
        map(fax).toPropertyWhenPresent("fax", record::fax)
        map(code).toPropertyWhenPresent("code", record::code)
        map(content01).toPropertyWhenPresent("content01", record::content01)
        map(content02).toPropertyWhenPresent("content02", record::content02)
        map(content03).toPropertyWhenPresent("content03", record::content03)
        map(content04).toPropertyWhenPresent("content04", record::content04)
        map(content05).toPropertyWhenPresent("content05", record::content05)
        map(invalid).toPropertyWhenPresent("invalid", record::invalid)
    }

private val columnList = listOf(id, name, address, tel, fax, code, content01, content02, content03, content04, content05, invalid)

fun AddressBookMapper.selectOne(completer: SelectCompleter) =
    selectOne(this::selectOne, columnList, AddressBook, completer)

fun AddressBookMapper.select(completer: SelectCompleter) =
    selectList(this::selectMany, columnList, AddressBook, completer)

fun AddressBookMapper.selectDistinct(completer: SelectCompleter) =
    selectDistinct(this::selectMany, columnList, AddressBook, completer)

fun AddressBookMapper.selectByPrimaryKey(id_: String) =
    selectOne {
        where(id, isEqualTo(id_))
    }

fun AddressBookMapper.update(completer: UpdateCompleter) =
    update(this::update, AddressBook, completer)

fun KotlinUpdateBuilder.updateAllColumns(record: AddressBookRecord) =
    apply {
        set(id).equalTo(record::id)
        set(name).equalTo(record::name)
        set(address).equalTo(record::address)
        set(tel).equalTo(record::tel)
        set(fax).equalTo(record::fax)
        set(code).equalTo(record::code)
        set(content01).equalTo(record::content01)
        set(content02).equalTo(record::content02)
        set(content03).equalTo(record::content03)
        set(content04).equalTo(record::content04)
        set(content05).equalTo(record::content05)
        set(invalid).equalTo(record::invalid)
    }

fun KotlinUpdateBuilder.updateSelectiveColumns(record: AddressBookRecord) =
    apply {
        set(id).equalToWhenPresent(record::id)
        set(name).equalToWhenPresent(record::name)
        set(address).equalToWhenPresent(record::address)
        set(tel).equalToWhenPresent(record::tel)
        set(fax).equalToWhenPresent(record::fax)
        set(code).equalToWhenPresent(record::code)
        set(content01).equalToWhenPresent(record::content01)
        set(content02).equalToWhenPresent(record::content02)
        set(content03).equalToWhenPresent(record::content03)
        set(content04).equalToWhenPresent(record::content04)
        set(content05).equalToWhenPresent(record::content05)
        set(invalid).equalToWhenPresent(record::invalid)
    }

fun AddressBookMapper.updateByPrimaryKey(record: AddressBookRecord) =
    update {
        set(name).equalTo(record::name)
        set(address).equalTo(record::address)
        set(tel).equalTo(record::tel)
        set(fax).equalTo(record::fax)
        set(code).equalTo(record::code)
        set(content01).equalTo(record::content01)
        set(content02).equalTo(record::content02)
        set(content03).equalTo(record::content03)
        set(content04).equalTo(record::content04)
        set(content05).equalTo(record::content05)
        set(invalid).equalTo(record::invalid)
        where(id, isEqualTo(record::id))
    }

fun AddressBookMapper.updateByPrimaryKeySelective(record: AddressBookRecord) =
    update {
        set(name).equalToWhenPresent(record::name)
        set(address).equalToWhenPresent(record::address)
        set(tel).equalToWhenPresent(record::tel)
        set(fax).equalToWhenPresent(record::fax)
        set(code).equalToWhenPresent(record::code)
        set(content01).equalToWhenPresent(record::content01)
        set(content02).equalToWhenPresent(record::content02)
        set(content03).equalToWhenPresent(record::content03)
        set(content04).equalToWhenPresent(record::content04)
        set(content05).equalToWhenPresent(record::content05)
        set(invalid).equalToWhenPresent(record::invalid)
        where(id, isEqualTo(record::id))
    }