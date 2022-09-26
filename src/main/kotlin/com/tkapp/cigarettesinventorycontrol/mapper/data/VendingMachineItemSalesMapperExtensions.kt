/*
 * Auto-generated file. Created by MyBatis Generator
 * Generation date: 2021-06-18T23:23:45.254701+09:00
 */
package com.tkapp.cigarettesinventorycontrol.mapper.data

import com.tkapp.cigarettesinventorycontrol.mapper.data.VendingMachineItemSalesDynamicSqlSupport.VendingMachineItemSales
import com.tkapp.cigarettesinventorycontrol.mapper.data.VendingMachineItemSalesDynamicSqlSupport.VendingMachineItemSales.id
import com.tkapp.cigarettesinventorycontrol.mapper.data.VendingMachineItemSalesDynamicSqlSupport.VendingMachineItemSales.itemId
import com.tkapp.cigarettesinventorycontrol.mapper.data.VendingMachineItemSalesDynamicSqlSupport.VendingMachineItemSales.salesDate
import com.tkapp.cigarettesinventorycontrol.mapper.data.VendingMachineItemSalesDynamicSqlSupport.VendingMachineItemSales.salesQuantity
import com.tkapp.cigarettesinventorycontrol.mapper.data.VendingMachineItemSalesDynamicSqlSupport.VendingMachineItemSales.vendingMachineId
import com.tkapp.cigarettesinventorycontrol.model.data.VendingMachineItemSalesRecord
import org.mybatis.dynamic.sql.SqlBuilder.isEqualTo
import org.mybatis.dynamic.sql.util.kotlin.*
import org.mybatis.dynamic.sql.util.kotlin.mybatis3.*

fun VendingMachineItemSalesMapper.count(completer: CountCompleter) =
    countFrom(this::count, VendingMachineItemSales, completer)

fun VendingMachineItemSalesMapper.delete(completer: DeleteCompleter) =
    deleteFrom(this::delete, VendingMachineItemSales, completer)

fun VendingMachineItemSalesMapper.deleteByPrimaryKey(id_: Int) =
    delete {
        where(id, isEqualTo(id_))
    }

fun VendingMachineItemSalesMapper.insert(record: VendingMachineItemSalesRecord) =
    insert(this::insert, record, VendingMachineItemSales) {
//        map(id).toProperty("id")
        map(salesQuantity).toProperty("salesQuantity")
        map(salesDate).toProperty("salesDate")
        map(vendingMachineId).toProperty("vendingMachineId")
        map(itemId).toProperty("itemId")
    }

fun VendingMachineItemSalesMapper.insertMultiple(records: Collection<VendingMachineItemSalesRecord>) =
    insertMultiple(this::insertMultiple, records, VendingMachineItemSales) {
        map(id).toProperty("id")
        map(salesQuantity).toProperty("salesQuantity")
        map(salesDate).toProperty("salesDate")
        map(vendingMachineId).toProperty("vendingMachineId")
        map(itemId).toProperty("itemId")
    }

fun VendingMachineItemSalesMapper.insertMultiple(vararg records: VendingMachineItemSalesRecord) =
    insertMultiple(records.toList())

fun VendingMachineItemSalesMapper.insertSelective(record: VendingMachineItemSalesRecord) =
    insert(this::insert, record, VendingMachineItemSales) {
        map(id).toPropertyWhenPresent("id", record::id)
        map(salesQuantity).toPropertyWhenPresent("salesQuantity", record::salesQuantity)
        map(salesDate).toPropertyWhenPresent("salesDate", record::salesDate)
        map(vendingMachineId).toPropertyWhenPresent("vendingMachineId", record::vendingMachineId)
        map(itemId).toPropertyWhenPresent("itemId", record::itemId)
    }

private val columnList = listOf(id, salesQuantity, salesDate, vendingMachineId, itemId)

fun VendingMachineItemSalesMapper.selectOne(completer: SelectCompleter) =
    selectOne(this::selectOne, columnList, VendingMachineItemSales, completer)

fun VendingMachineItemSalesMapper.select(completer: SelectCompleter) =
    selectList(this::selectMany, columnList, VendingMachineItemSales, completer)

fun VendingMachineItemSalesMapper.selectDistinct(completer: SelectCompleter) =
    selectDistinct(this::selectMany, columnList, VendingMachineItemSales, completer)

fun VendingMachineItemSalesMapper.selectByPrimaryKey(id_: Int) =
    selectOne {
        where(id, isEqualTo(id_))
    }

fun VendingMachineItemSalesMapper.update(completer: UpdateCompleter) =
    update(this::update, VendingMachineItemSales, completer)

fun KotlinUpdateBuilder.updateAllColumns(record: VendingMachineItemSalesRecord) =
    apply {
        set(id).equalTo(record::id)
        set(salesQuantity).equalTo(record::salesQuantity)
        set(salesDate).equalTo(record::salesDate)
        set(vendingMachineId).equalTo(record::vendingMachineId)
        set(itemId).equalTo(record::itemId)
    }

fun KotlinUpdateBuilder.updateSelectiveColumns(record: VendingMachineItemSalesRecord) =
    apply {
        set(id).equalToWhenPresent(record::id)
        set(salesQuantity).equalToWhenPresent(record::salesQuantity)
        set(salesDate).equalToWhenPresent(record::salesDate)
        set(vendingMachineId).equalToWhenPresent(record::vendingMachineId)
        set(itemId).equalToWhenPresent(record::itemId)
    }

fun VendingMachineItemSalesMapper.updateByPrimaryKey(record: VendingMachineItemSalesRecord) =
    update {
        set(salesQuantity).equalTo(record::salesQuantity)
        set(salesDate).equalTo(record::salesDate)
        set(vendingMachineId).equalTo(record::vendingMachineId)
        set(itemId).equalTo(record::itemId)
        where(id, isEqualTo(record::id))
    }

fun VendingMachineItemSalesMapper.updateByPrimaryKeySelective(record: VendingMachineItemSalesRecord) =
    update {
        set(salesQuantity).equalToWhenPresent(record::salesQuantity)
        set(salesDate).equalToWhenPresent(record::salesDate)
        set(vendingMachineId).equalToWhenPresent(record::vendingMachineId)
        set(itemId).equalToWhenPresent(record::itemId)
        where(id, isEqualTo(record::id))
    }