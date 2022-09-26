/*
 * Auto-generated file. Created by MyBatis Generator
 * Generation date: 2021-06-18T23:23:45.250429+09:00
 */
package com.tkapp.cigarettesinventorycontrol.mapper.data

import com.tkapp.cigarettesinventorycontrol.mapper.data.TaxDynamicSqlSupport.Tax
import com.tkapp.cigarettesinventorycontrol.mapper.data.TaxDynamicSqlSupport.Tax.date
import com.tkapp.cigarettesinventorycontrol.mapper.data.TaxDynamicSqlSupport.Tax.id
import com.tkapp.cigarettesinventorycontrol.mapper.data.TaxDynamicSqlSupport.Tax.taxRate
import com.tkapp.cigarettesinventorycontrol.model.data.TaxRecord
import org.mybatis.dynamic.sql.SqlBuilder.isEqualTo
import org.mybatis.dynamic.sql.util.kotlin.*
import org.mybatis.dynamic.sql.util.kotlin.mybatis3.*

fun TaxMapper.count(completer: CountCompleter) =
    countFrom(this::count, Tax, completer)

fun TaxMapper.delete(completer: DeleteCompleter) =
    deleteFrom(this::delete, Tax, completer)

fun TaxMapper.deleteByPrimaryKey(id_: Int) =
    delete {
        where(id, isEqualTo(id_))
    }

fun TaxMapper.insert(record: TaxRecord) =
    insert(this::insert, record, Tax) {
//        map(id).toProperty("id")
        map(taxRate).toProperty("taxRate")
        map(date).toProperty("date")
    }

fun TaxMapper.insertMultiple(records: Collection<TaxRecord>) =
    insertMultiple(this::insertMultiple, records, Tax) {
        map(id).toProperty("id")
        map(taxRate).toProperty("taxRate")
        map(date).toProperty("date")
    }

fun TaxMapper.insertMultiple(vararg records: TaxRecord) =
    insertMultiple(records.toList())

fun TaxMapper.insertSelective(record: TaxRecord) =
    insert(this::insert, record, Tax) {
        map(id).toPropertyWhenPresent("id", record::id)
        map(taxRate).toPropertyWhenPresent("taxRate", record::taxRate)
        map(date).toPropertyWhenPresent("date", record::date)
    }

private val columnList = listOf(id, taxRate, date)

fun TaxMapper.selectOne(completer: SelectCompleter) =
    selectOne(this::selectOne, columnList, Tax, completer)

fun TaxMapper.select(completer: SelectCompleter) =
    selectList(this::selectMany, columnList, Tax, completer)

fun TaxMapper.selectDistinct(completer: SelectCompleter) =
    selectDistinct(this::selectMany, columnList, Tax, completer)

fun TaxMapper.selectByPrimaryKey(id_: Int) =
    selectOne {
        where(id, isEqualTo(id_))
    }

fun TaxMapper.update(completer: UpdateCompleter) =
    update(this::update, Tax, completer)

fun KotlinUpdateBuilder.updateAllColumns(record: TaxRecord) =
    apply {
        set(id).equalTo(record::id)
        set(taxRate).equalTo(record::taxRate)
        set(date).equalTo(record::date)
    }

fun KotlinUpdateBuilder.updateSelectiveColumns(record: TaxRecord) =
    apply {
        set(id).equalToWhenPresent(record::id)
        set(taxRate).equalToWhenPresent(record::taxRate)
        set(date).equalToWhenPresent(record::date)
    }

fun TaxMapper.updateByPrimaryKey(record: TaxRecord) =
    update {
        set(taxRate).equalTo(record::taxRate)
        set(date).equalTo(record::date)
        where(id, isEqualTo(record::id))
    }

fun TaxMapper.updateByPrimaryKeySelective(record: TaxRecord) =
    update {
        set(taxRate).equalToWhenPresent(record::taxRate)
        set(date).equalToWhenPresent(record::date)
        where(id, isEqualTo(record::id))
    }