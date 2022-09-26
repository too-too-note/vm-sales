/*
 * Auto-generated file. Created by MyBatis Generator
 * Generation date: 2021-07-06T22:30:47.628446+09:00
 */
package com.tkapp.cigarettesinventorycontrol.mapper.data

import com.tkapp.cigarettesinventorycontrol.model.data.UserzRecord
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
interface UserzMapper {
    @SelectProvider(type=SqlProviderAdapter::class, method="select")
    fun count(selectStatement: SelectStatementProvider): Long

    @DeleteProvider(type=SqlProviderAdapter::class, method="delete")
    fun delete(deleteStatement: DeleteStatementProvider): Int

    @InsertProvider(type=SqlProviderAdapter::class, method="insert")
    fun insert(insertStatement: InsertStatementProvider<UserzRecord>): Int

    @InsertProvider(type=SqlProviderAdapter::class, method="insertMultiple")
    fun insertMultiple(multipleInsertStatement: MultiRowInsertStatementProvider<UserzRecord>): Int

    @SelectProvider(type=SqlProviderAdapter::class, method="select")
    @ResultMap("UserzRecordResult")
    fun selectOne(selectStatement: SelectStatementProvider): UserzRecord?

    @SelectProvider(type=SqlProviderAdapter::class, method="select")
    @Results(id="UserzRecordResult", value = [
        Result(column="user_id", property="userId", jdbcType=JdbcType.VARCHAR, id=true),
        Result(column="user_name", property="userName", jdbcType=JdbcType.VARCHAR),
        Result(column="mail_address", property="mailAddress", jdbcType=JdbcType.VARCHAR),
        Result(column="tel", property="tel", jdbcType=JdbcType.VARCHAR),
        Result(column="invalid", property="invalid", jdbcType=JdbcType.BIT),
        Result(column="password", property="password", jdbcType=JdbcType.VARCHAR),
        Result(column="secret", property="secret", jdbcType=JdbcType.VARCHAR)
    ])
    fun selectMany(selectStatement: SelectStatementProvider): List<UserzRecord>

    @UpdateProvider(type=SqlProviderAdapter::class, method="update")
    fun update(updateStatement: UpdateStatementProvider): Int
}