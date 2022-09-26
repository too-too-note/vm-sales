/*
 * Auto-generated file. Created by MyBatis Generator
 * Generation date: 2021-06-18T23:23:45.253697+09:00
 */
package com.tkapp.cigarettesinventorycontrol.mapper.data

import com.tkapp.cigarettesinventorycontrol.mapper.data.VendingMachineDynamicSqlSupport.VendingMachine
import com.tkapp.cigarettesinventorycontrol.mapper.data.VendingMachineDynamicSqlSupport.VendingMachine.address
import com.tkapp.cigarettesinventorycontrol.mapper.data.VendingMachineDynamicSqlSupport.VendingMachine.feeFixed
import com.tkapp.cigarettesinventorycontrol.mapper.data.VendingMachineDynamicSqlSupport.VendingMachine.feeRate
import com.tkapp.cigarettesinventorycontrol.mapper.data.VendingMachineDynamicSqlSupport.VendingMachine.id
import com.tkapp.cigarettesinventorycontrol.mapper.data.VendingMachineDynamicSqlSupport.VendingMachine.invalid
import com.tkapp.cigarettesinventorycontrol.mapper.data.VendingMachineDynamicSqlSupport.VendingMachine.orderSeq
import com.tkapp.cigarettesinventorycontrol.mapper.data.VendingMachineDynamicSqlSupport.VendingMachine.vendingMachineCode
import com.tkapp.cigarettesinventorycontrol.mapper.data.VendingMachineDynamicSqlSupport.VendingMachine.vendingMachineName
import com.tkapp.cigarettesinventorycontrol.model.data.VendingMachineRecord
import org.mybatis.dynamic.sql.SqlBuilder.isEqualTo
import org.mybatis.dynamic.sql.util.kotlin.*
import org.mybatis.dynamic.sql.util.kotlin.mybatis3.*

fun VendingMachineMapper.count(completer: CountCompleter) =
    countFrom(this::count, VendingMachine, completer)

fun VendingMachineMapper.delete(completer: DeleteCompleter) =
    deleteFrom(this::delete, VendingMachine, completer)

fun VendingMachineMapper.deleteByPrimaryKey(id_: Int) =
    delete {
        where(id, isEqualTo(id_))
    }

fun VendingMachineMapper.insert(record: VendingMachineRecord) =
    insert(this::insert, record, VendingMachine) {
//        map(id).toProperty("id")
        map(vendingMachineName).toProperty("vendingMachineName")
        map(address).toProperty("address")
        map(vendingMachineCode).toProperty("vendingMachineCode")
        map(invalid).toProperty("invalid")
        map(orderSeq).toProperty("orderSeq")
        map(feeRate).toProperty("feeRate")
        map(feeFixed).toProperty("feeFixed")
    }

fun VendingMachineMapper.insertMultiple(records: Collection<VendingMachineRecord>) =
    insertMultiple(this::insertMultiple, records, VendingMachine) {
        map(id).toProperty("id")
        map(vendingMachineName).toProperty("vendingMachineName")
        map(address).toProperty("address")
        map(vendingMachineCode).toProperty("vendingMachineCode")
        map(invalid).toProperty("invalid")
        map(orderSeq).toProperty("orderSeq")
        map(feeRate).toProperty("feeRate")
        map(feeFixed).toProperty("feeFixed")
    }

fun VendingMachineMapper.insertMultiple(vararg records: VendingMachineRecord) =
    insertMultiple(records.toList())

fun VendingMachineMapper.insertSelective(record: VendingMachineRecord) =
    insert(this::insert, record, VendingMachine) {
        map(id).toPropertyWhenPresent("id", record::id)
        map(vendingMachineName).toPropertyWhenPresent("vendingMachineName", record::vendingMachineName)
        map(address).toPropertyWhenPresent("address", record::address)
        map(vendingMachineCode).toPropertyWhenPresent("vendingMachineCode", record::vendingMachineCode)
        map(invalid).toPropertyWhenPresent("invalid", record::invalid)
        map(orderSeq).toPropertyWhenPresent("orderSeq", record::orderSeq)
        map(feeRate).toPropertyWhenPresent("feeRate", record::feeRate)
        map(feeFixed).toPropertyWhenPresent("feeFixed", record::feeFixed)
    }

private val columnList = listOf(id, vendingMachineName, address, vendingMachineCode, invalid, orderSeq, feeRate, feeFixed)

fun VendingMachineMapper.selectOne(completer: SelectCompleter) =
    selectOne(this::selectOne, columnList, VendingMachine, completer)

fun VendingMachineMapper.select(completer: SelectCompleter) =
    selectList(this::selectMany, columnList, VendingMachine, completer)

fun VendingMachineMapper.selectDistinct(completer: SelectCompleter) =
    selectDistinct(this::selectMany, columnList, VendingMachine, completer)

fun VendingMachineMapper.selectByPrimaryKey(id_: Int) =
    selectOne {
        where(id, isEqualTo(id_))
    }

fun VendingMachineMapper.update(completer: UpdateCompleter) =
    update(this::update, VendingMachine, completer)

fun KotlinUpdateBuilder.updateAllColumns(record: VendingMachineRecord) =
    apply {
        set(id).equalTo(record::id)
        set(vendingMachineName).equalTo(record::vendingMachineName)
        set(address).equalTo(record::address)
        set(vendingMachineCode).equalTo(record::vendingMachineCode)
        set(invalid).equalTo(record::invalid)
        set(orderSeq).equalTo(record::orderSeq)
        set(feeRate).equalTo(record::feeRate)
        set(feeFixed).equalTo(record::feeFixed)
    }

fun KotlinUpdateBuilder.updateSelectiveColumns(record: VendingMachineRecord) =
    apply {
        set(id).equalToWhenPresent(record::id)
        set(vendingMachineName).equalToWhenPresent(record::vendingMachineName)
        set(address).equalToWhenPresent(record::address)
        set(vendingMachineCode).equalToWhenPresent(record::vendingMachineCode)
        set(invalid).equalToWhenPresent(record::invalid)
        set(orderSeq).equalToWhenPresent(record::orderSeq)
        set(feeRate).equalToWhenPresent(record::feeRate)
        set(feeFixed).equalToWhenPresent(record::feeFixed)
    }

fun VendingMachineMapper.updateByPrimaryKey(record: VendingMachineRecord) =
    update {
        set(vendingMachineName).equalTo(record::vendingMachineName)
        set(address).equalTo(record::address)
        set(vendingMachineCode).equalTo(record::vendingMachineCode)
        set(invalid).equalTo(record::invalid)
        set(orderSeq).equalTo(record::orderSeq)
        set(feeRate).equalTo(record::feeRate)
        set(feeFixed).equalTo(record::feeFixed)
        where(id, isEqualTo(record::id))
    }

fun VendingMachineMapper.updateByPrimaryKeySelective(record: VendingMachineRecord) =
    update {
        set(vendingMachineName).equalToWhenPresent(record::vendingMachineName)
        set(address).equalToWhenPresent(record::address)
        set(vendingMachineCode).equalToWhenPresent(record::vendingMachineCode)
        set(invalid).equalToWhenPresent(record::invalid)
        set(orderSeq).equalToWhenPresent(record::orderSeq)
        set(feeRate).equalToWhenPresent(record::feeRate)
        set(feeFixed).equalToWhenPresent(record::feeFixed)
        where(id, isEqualTo(record::id))
    }