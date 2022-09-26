/*
 * Auto-generated file. Created by MyBatis Generator
 * Generation date: 2021-06-18T23:23:45.252567+09:00
 */
package com.tkapp.cigarettesinventorycontrol.mapper.data

import com.tkapp.cigarettesinventorycontrol.mapper.data.ItemDynamicSqlSupport.Item
import com.tkapp.cigarettesinventorycontrol.mapper.data.ItemDynamicSqlSupport.Item.id
import com.tkapp.cigarettesinventorycontrol.mapper.data.ItemDynamicSqlSupport.Item.invalid
import com.tkapp.cigarettesinventorycontrol.mapper.data.ItemDynamicSqlSupport.Item.itemCode
import com.tkapp.cigarettesinventorycontrol.mapper.data.ItemDynamicSqlSupport.Item.itemName
import com.tkapp.cigarettesinventorycontrol.mapper.data.ItemDynamicSqlSupport.Item.orderSeq
import com.tkapp.cigarettesinventorycontrol.mapper.data.ItemDynamicSqlSupport.Item.price
import com.tkapp.cigarettesinventorycontrol.model.data.ItemRecord
import org.mybatis.dynamic.sql.SqlBuilder.isEqualTo
import org.mybatis.dynamic.sql.util.kotlin.*
import org.mybatis.dynamic.sql.util.kotlin.mybatis3.*

fun ItemMapper.count(completer: CountCompleter) =
    countFrom(this::count, Item, completer)

fun ItemMapper.delete(completer: DeleteCompleter) =
    deleteFrom(this::delete, Item, completer)

fun ItemMapper.deleteByPrimaryKey(id_: Int) =
    delete {
        where(id, isEqualTo(id_))
    }

fun ItemMapper.insert(record: ItemRecord) =
    insert(this::insert, record, Item) {
//        map(id).toProperty("id")
        map(itemCode).toProperty("itemCode")
        map(itemName).toProperty("itemName")
        map(price).toProperty("price")
        map(orderSeq).toProperty("orderSeq")
        map(invalid).toProperty("invalid")
    }

fun ItemMapper.insertMultiple(records: Collection<ItemRecord>) =
    insertMultiple(this::insertMultiple, records, Item) {
        map(id).toProperty("id")
        map(itemCode).toProperty("itemCode")
        map(itemName).toProperty("itemName")
        map(price).toProperty("price")
        map(orderSeq).toProperty("orderSeq")
        map(invalid).toProperty("invalid")
    }

fun ItemMapper.insertMultiple(vararg records: ItemRecord) =
    insertMultiple(records.toList())

fun ItemMapper.insertSelective(record: ItemRecord) =
    insert(this::insert, record, Item) {
        map(id).toPropertyWhenPresent("id", record::id)
        map(itemCode).toPropertyWhenPresent("itemCode", record::itemCode)
        map(itemName).toPropertyWhenPresent("itemName", record::itemName)
        map(price).toPropertyWhenPresent("price", record::price)
        map(orderSeq).toPropertyWhenPresent("orderSeq", record::orderSeq)
        map(invalid).toPropertyWhenPresent("invalid", record::invalid)
    }

private val columnList = listOf(id, itemCode, itemName, price, orderSeq, invalid)

fun ItemMapper.selectOne(completer: SelectCompleter) =
    selectOne(this::selectOne, columnList, Item, completer)

fun ItemMapper.select(completer: SelectCompleter) =
    selectList(this::selectMany, columnList, Item, completer)

fun ItemMapper.selectDistinct(completer: SelectCompleter) =
    selectDistinct(this::selectMany, columnList, Item, completer)

fun ItemMapper.selectByPrimaryKey(id_: Int) =
    selectOne {
        where(id, isEqualTo(id_))
    }

fun ItemMapper.update(completer: UpdateCompleter) =
    update(this::update, Item, completer)

fun KotlinUpdateBuilder.updateAllColumns(record: ItemRecord) =
    apply {
        set(id).equalTo(record::id)
        set(itemCode).equalTo(record::itemCode)
        set(itemName).equalTo(record::itemName)
        set(price).equalTo(record::price)
        set(orderSeq).equalTo(record::orderSeq)
        set(invalid).equalTo(record::invalid)
    }

fun KotlinUpdateBuilder.updateSelectiveColumns(record: ItemRecord) =
    apply {
        set(id).equalToWhenPresent(record::id)
        set(itemCode).equalToWhenPresent(record::itemCode)
        set(itemName).equalToWhenPresent(record::itemName)
        set(price).equalToWhenPresent(record::price)
        set(orderSeq).equalToWhenPresent(record::orderSeq)
        set(invalid).equalToWhenPresent(record::invalid)
    }

fun ItemMapper.updateByPrimaryKey(record: ItemRecord) =
    update {
        set(itemCode).equalTo(record::itemCode)
        set(itemName).equalTo(record::itemName)
        set(price).equalTo(record::price)
        set(orderSeq).equalTo(record::orderSeq)
        set(invalid).equalTo(record::invalid)
        where(id, isEqualTo(record::id))
    }

fun ItemMapper.updateByPrimaryKeySelective(record: ItemRecord) =
    update {
        set(itemCode).equalToWhenPresent(record::itemCode)
        set(itemName).equalToWhenPresent(record::itemName)
        set(price).equalToWhenPresent(record::price)
        set(orderSeq).equalToWhenPresent(record::orderSeq)
        set(invalid).equalToWhenPresent(record::invalid)
        where(id, isEqualTo(record::id))
    }