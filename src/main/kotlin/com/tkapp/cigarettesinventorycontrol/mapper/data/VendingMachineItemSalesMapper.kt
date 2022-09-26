/*
 * Auto-generated file. Created by MyBatis Generator
 * Generation date: 2021-06-18T23:18:40.599048+09:00
 */
package com.tkapp.cigarettesinventorycontrol.mapper.data

import com.tkapp.cigarettesinventorycontrol.controller.salesinquiry.SalesInquirySelectForm
import com.tkapp.cigarettesinventorycontrol.model.data.VendingMachineItemSalesJoinedRecord
import com.tkapp.cigarettesinventorycontrol.model.data.VendingMachineItemSalesRecord
import org.apache.ibatis.annotations.*
import org.apache.ibatis.jdbc.SQL
import org.apache.ibatis.type.JdbcType
import org.mybatis.dynamic.sql.delete.render.DeleteStatementProvider
import org.mybatis.dynamic.sql.insert.render.InsertStatementProvider
import org.mybatis.dynamic.sql.insert.render.MultiRowInsertStatementProvider
import org.mybatis.dynamic.sql.select.render.SelectStatementProvider
import org.mybatis.dynamic.sql.update.render.UpdateStatementProvider
import org.mybatis.dynamic.sql.util.SqlProviderAdapter

@Mapper
interface VendingMachineItemSalesMapper {
    @SelectProvider(type=SqlProviderAdapter::class, method="select")
    fun count(selectStatement: SelectStatementProvider): Long

    @DeleteProvider(type=SqlProviderAdapter::class, method="delete")
    fun delete(deleteStatement: DeleteStatementProvider): Int

    @InsertProvider(type=SqlProviderAdapter::class, method="insert")
    fun insert(insertStatement: InsertStatementProvider<VendingMachineItemSalesRecord>): Int

    @InsertProvider(type=SqlProviderAdapter::class, method="insertMultiple")
    fun insertMultiple(multipleInsertStatement: MultiRowInsertStatementProvider<VendingMachineItemSalesRecord>): Int

    @SelectProvider(type=SqlProviderAdapter::class, method="select")
    @ResultMap("VendingMachineItemSalesRecordResult")
    fun selectOne(selectStatement: SelectStatementProvider): VendingMachineItemSalesRecord?

    @SelectProvider(type=SqlProviderAdapter::class, method="select")
    @Results(id="VendingMachineItemSalesRecordResult", value = [
        Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        Result(column="sales_quantity", property="salesQuantity", jdbcType=JdbcType.INTEGER),
        Result(column="sales_date", property="salesDate", jdbcType=JdbcType.DATE),
        Result(column="vending_machine_id", property="vendingMachineId", jdbcType=JdbcType.INTEGER),
        Result(column="item_id", property="itemId", jdbcType=JdbcType.INTEGER)
    ])
    fun selectMany(selectStatement: SelectStatementProvider): List<VendingMachineItemSalesRecord>

    @UpdateProvider(type=SqlProviderAdapter::class, method="update")
    fun update(updateStatement: UpdateStatementProvider): Int

    @Select("""
        <script>
            SELECT
             t1.id,
             t1.sales_quantity as salesQuantity,
             t1.sales_date as salesDate,
             
             t3.id as itemId,
             t3.item_code as itemCode,
             t3.item_name as itemName,
             t3.price as price,
             t3.order_seq as itemOrderSeq,
             
             t4.id as vendingMachineId,
             t4.vending_machine_name as vendingMachineName,
             t4.vending_machine_code as vendingMachineCode,
             t4.address as address,
             t4.order_seq as vendingMachineOrderSeq
             
            FROM
             vending_machine_item_sales t1
             INNER JOIN item t3 on t1.item_id = t3.id
             INNER JOIN vending_machine t4 on t1.vending_machine_id = t4.id
            <where>
                <if test="searchByDateList == true">
                    t1.sales_date in
                    <foreach item="date" collection="dateList"
                        open="(" separator="," close=")">
                          #{date}
                    </foreach>
                </if>
                <if test="searchByDateList == false">
                    t1.sales_date BETWEEN #{fromDate} AND #{toDate}
                </if>
                <if test="itemCode != null and itemCode != ''">
                    AND t3.item_code like '%' || #{itemCode} || '%'
                </if>
                <if test="itemName != null and itemName != ''">
                    AND t3.item_name like '%' || #{itemName} || '%'
                </if>
                <if test="vendingMachineCode != null and vendingMachineCode != ''">
                    AND t4.vending_machine_code like '%' || #{vendingMachineCode} || '%'
                </if>
                <if test="vendingMachineName != null and vendingMachineName != ''">
                    AND t4.vending_machine_name like '%' || #{vendingMachineName} || '%'
                </if>
                <if test="address != null and address != ''">
                    AND t4.address like '%' || #{address} || '%'
                </if>
            </where>
        </script>
    """)
    fun selectBySalesInquirySelectForm(form: SalesInquirySelectForm): Collection<VendingMachineItemSalesJoinedRecord>
}