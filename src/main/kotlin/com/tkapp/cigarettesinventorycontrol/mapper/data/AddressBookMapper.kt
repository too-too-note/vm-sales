/*
 * Auto-generated file. Created by MyBatis Generator
 * Generation date: 2021-06-27T17:39:43.485699+09:00
 */
package com.tkapp.cigarettesinventorycontrol.mapper.data

import com.tkapp.cigarettesinventorycontrol.model.data.AddressBookRecord
import org.apache.ibatis.annotations.DeleteProvider
import org.apache.ibatis.annotations.InsertProvider
import org.apache.ibatis.annotations.Mapper
import org.apache.ibatis.annotations.Result
import org.apache.ibatis.annotations.ResultMap
import org.apache.ibatis.annotations.Results
import org.apache.ibatis.annotations.SelectProvider
import org.apache.ibatis.annotations.UpdateProvider
import org.apache.ibatis.type.JdbcType
import org.mybatis.dynamic.sql.delete.render.DeleteStatementProvider
import org.mybatis.dynamic.sql.insert.render.InsertStatementProvider
import org.mybatis.dynamic.sql.insert.render.MultiRowInsertStatementProvider
import org.mybatis.dynamic.sql.select.render.SelectStatementProvider
import org.mybatis.dynamic.sql.update.render.UpdateStatementProvider
import org.mybatis.dynamic.sql.util.SqlProviderAdapter

@Mapper
interface AddressBookMapper {
    @SelectProvider(type=SqlProviderAdapter::class, method="select")
    fun count(selectStatement: SelectStatementProvider): Long

    @DeleteProvider(type=SqlProviderAdapter::class, method="delete")
    fun delete(deleteStatement: DeleteStatementProvider): Int

    @InsertProvider(type=SqlProviderAdapter::class, method="insert")
    fun insert(insertStatement: InsertStatementProvider<AddressBookRecord>): Int

    @InsertProvider(type=SqlProviderAdapter::class, method="insertMultiple")
    fun insertMultiple(multipleInsertStatement: MultiRowInsertStatementProvider<AddressBookRecord>): Int

    @SelectProvider(type=SqlProviderAdapter::class, method="select")
    @ResultMap("AddressBookRecordResult")
    fun selectOne(selectStatement: SelectStatementProvider): AddressBookRecord?

    @SelectProvider(type=SqlProviderAdapter::class, method="select")
    @Results(id="AddressBookRecordResult", value = [
        Result(column="id", property="id", jdbcType=JdbcType.VARCHAR, id=true),
        Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
        Result(column="address", property="address", jdbcType=JdbcType.VARCHAR),
        Result(column="tel", property="tel", jdbcType=JdbcType.VARCHAR),
        Result(column="fax", property="fax", jdbcType=JdbcType.VARCHAR),
        Result(column="code", property="code", jdbcType=JdbcType.VARCHAR),
        Result(column="content01", property="content01", jdbcType=JdbcType.VARCHAR),
        Result(column="content02", property="content02", jdbcType=JdbcType.VARCHAR),
        Result(column="content03", property="content03", jdbcType=JdbcType.VARCHAR),
        Result(column="content04", property="content04", jdbcType=JdbcType.VARCHAR),
        Result(column="content05", property="content05", jdbcType=JdbcType.VARCHAR),
        Result(column="invalid", property="invalid", jdbcType=JdbcType.BIT)
    ])
    fun selectMany(selectStatement: SelectStatementProvider): List<AddressBookRecord>

    @UpdateProvider(type=SqlProviderAdapter::class, method="update")
    fun update(updateStatement: UpdateStatementProvider): Int
}