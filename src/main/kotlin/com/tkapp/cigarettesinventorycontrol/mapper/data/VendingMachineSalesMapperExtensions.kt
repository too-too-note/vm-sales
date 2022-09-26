/*
 * Auto-generated file. Created by MyBatis Generator
 * Generation date: 2021-06-18T23:23:45.239579+09:00
 */
package com.tkapp.cigarettesinventorycontrol.mapper.data

import com.tkapp.cigarettesinventorycontrol.mapper.data.VendingMachineSalesDynamicSqlSupport.VendingMachineSales
import com.tkapp.cigarettesinventorycontrol.mapper.data.VendingMachineSalesDynamicSqlSupport.VendingMachineSales.id
import com.tkapp.cigarettesinventorycontrol.mapper.data.VendingMachineSalesDynamicSqlSupport.VendingMachineSales.salesDate
import com.tkapp.cigarettesinventorycontrol.mapper.data.VendingMachineSalesDynamicSqlSupport.VendingMachineSales.salesPriceActual
import com.tkapp.cigarettesinventorycontrol.mapper.data.VendingMachineSalesDynamicSqlSupport.VendingMachineSales.salesPriceOnDisplay
import com.tkapp.cigarettesinventorycontrol.mapper.data.VendingMachineSalesDynamicSqlSupport.VendingMachineSales.vendingMachineId
import com.tkapp.cigarettesinventorycontrol.model.data.VendingMachineSalesRecord
import org.mybatis.dynamic.sql.SqlBuilder.isEqualTo
import org.mybatis.dynamic.sql.util.kotlin.*
import org.mybatis.dynamic.sql.util.kotlin.mybatis3.*

fun VendingMachineSalesMapper.count(completer: CountCompleter) =
    countFrom(this::count, VendingMachineSales, completer)

fun VendingMachineSalesMapper.delete(completer: DeleteCompleter) =
    deleteFrom(this::delete, VendingMachineSales, completer)

fun VendingMachineSalesMapper.deleteByPrimaryKey(id_: Int) =
    delete {
        where(id, isEqualTo(id_))
    }

fun VendingMachineSalesMapper.insert(record: VendingMachineSalesRecord) =
    insert(this::insert, record, VendingMachineSales) {
//        map(id).toProperty("id")
        map(salesPriceOnDisplay).toProperty("salesPriceOnDisplay")
        map(salesPriceActual).toProperty("salesPriceActual")
        map(salesDate).toProperty("salesDate")
        map(vendingMachineId).toProperty("vendingMachineId")
    }

fun VendingMachineSalesMapper.insertMultiple(records: Collection<VendingMachineSalesRecord>) =
    insertMultiple(this::insertMultiple, records, VendingMachineSales) {
        map(id).toProperty("id")
        map(salesPriceOnDisplay).toProperty("salesPriceOnDisplay")
        map(salesPriceActual).toProperty("salesPriceActual")
        map(salesDate).toProperty("salesDate")
        map(vendingMachineId).toProperty("vendingMachineId")
    }

fun VendingMachineSalesMapper.insertMultiple(vararg records: VendingMachineSalesRecord) =
    insertMultiple(records.toList())

fun VendingMachineSalesMapper.insertSelective(record: VendingMachineSalesRecord) =
    insert(this::insert, record, VendingMachineSales) {
        map(id).toPropertyWhenPresent("id", record::id)
        map(salesPriceOnDisplay).toPropertyWhenPresent("salesPriceOnDisplay", record::salesPriceOnDisplay)
        map(salesPriceActual).toPropertyWhenPresent("salesPriceActual", record::salesPriceActual)
        map(salesDate).toPropertyWhenPresent("salesDate", record::salesDate)
        map(vendingMachineId).toPropertyWhenPresent("vendingMachineId", record::vendingMachineId)
    }

private val columnList = listOf(id, salesPriceOnDisplay, salesPriceActual, salesDate, vendingMachineId)

fun VendingMachineSalesMapper.selectOne(completer: SelectCompleter) =
    selectOne(this::selectOne, columnList, VendingMachineSales, completer)

fun VendingMachineSalesMapper.select(completer: SelectCompleter) =
    selectList(this::selectMany, columnList, VendingMachineSales, completer)

fun VendingMachineSalesMapper.selectDistinct(completer: SelectCompleter) =
    selectDistinct(this::selectMany, columnList, VendingMachineSales, completer)

fun VendingMachineSalesMapper.selectByPrimaryKey(id_: Int) =
    selectOne {
        where(id, isEqualTo(id_))
    }

fun VendingMachineSalesMapper.update(completer: UpdateCompleter) =
    update(this::update, VendingMachineSales, completer)

fun KotlinUpdateBuilder.updateAllColumns(record: VendingMachineSalesRecord) =
    apply {
        set(id).equalTo(record::id)
        set(salesPriceOnDisplay).equalTo(record::salesPriceOnDisplay)
        set(salesPriceActual).equalTo(record::salesPriceActual)
        set(salesDate).equalTo(record::salesDate)
        set(vendingMachineId).equalTo(record::vendingMachineId)
    }

fun KotlinUpdateBuilder.updateSelectiveColumns(record: VendingMachineSalesRecord) =
    apply {
        set(id).equalToWhenPresent(record::id)
        set(salesPriceOnDisplay).equalToWhenPresent(record::salesPriceOnDisplay)
        set(salesPriceActual).equalToWhenPresent(record::salesPriceActual)
        set(salesDate).equalToWhenPresent(record::salesDate)
        set(vendingMachineId).equalToWhenPresent(record::vendingMachineId)
    }

fun VendingMachineSalesMapper.updateByPrimaryKey(record: VendingMachineSalesRecord) =
    update {
        set(salesPriceOnDisplay).equalTo(record::salesPriceOnDisplay)
        set(salesPriceActual).equalTo(record::salesPriceActual)
        set(salesDate).equalTo(record::salesDate)
        set(vendingMachineId).equalTo(record::vendingMachineId)
        where(id, isEqualTo(record::id))
    }

fun VendingMachineSalesMapper.updateByPrimaryKeySelective(record: VendingMachineSalesRecord) =
    update {
        set(salesPriceOnDisplay).equalToWhenPresent(record::salesPriceOnDisplay)
        set(salesPriceActual).equalToWhenPresent(record::salesPriceActual)
        set(salesDate).equalToWhenPresent(record::salesDate)
        set(vendingMachineId).equalToWhenPresent(record::vendingMachineId)
        where(id, isEqualTo(record::id))
    }