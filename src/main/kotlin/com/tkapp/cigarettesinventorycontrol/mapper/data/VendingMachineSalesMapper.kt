/*
 * Auto-generated file. Created by MyBatis Generator
 * Generation date: 2021-06-18T23:23:45.233679+09:00
 */
package com.tkapp.cigarettesinventorycontrol.mapper.data

import com.tkapp.cigarettesinventorycontrol.controller.vmfeeinquiry.VmFeeInquirySelectForm
import com.tkapp.cigarettesinventorycontrol.model.data.VendingMachineSalesJoinedRecord
import com.tkapp.cigarettesinventorycontrol.model.data.VendingMachineSalesRecord
import org.apache.ibatis.annotations.*
import org.apache.ibatis.type.JdbcType
import org.mybatis.dynamic.sql.delete.render.DeleteStatementProvider
import org.mybatis.dynamic.sql.insert.render.InsertStatementProvider
import org.mybatis.dynamic.sql.insert.render.MultiRowInsertStatementProvider
import org.mybatis.dynamic.sql.select.render.SelectStatementProvider
import org.mybatis.dynamic.sql.update.render.UpdateStatementProvider
import org.mybatis.dynamic.sql.util.SqlProviderAdapter

@Mapper
interface VendingMachineSalesMapper {
    @SelectProvider(type=SqlProviderAdapter::class, method="select")
    fun count(selectStatement: SelectStatementProvider): Long

    @DeleteProvider(type=SqlProviderAdapter::class, method="delete")
    fun delete(deleteStatement: DeleteStatementProvider): Int

    @InsertProvider(type=SqlProviderAdapter::class, method="insert")
    fun insert(insertStatement: InsertStatementProvider<VendingMachineSalesRecord>): Int

    @InsertProvider(type=SqlProviderAdapter::class, method="insertMultiple")
    fun insertMultiple(multipleInsertStatement: MultiRowInsertStatementProvider<VendingMachineSalesRecord>): Int

    @SelectProvider(type=SqlProviderAdapter::class, method="select")
    @ResultMap("VendingMachineSalesRecordResult")
    fun selectOne(selectStatement: SelectStatementProvider): VendingMachineSalesRecord?

    @SelectProvider(type=SqlProviderAdapter::class, method="select")
    @Results(id="VendingMachineSalesRecordResult", value = [
        Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        Result(column="sales_price_on_display", property="salesPriceOnDisplay", jdbcType=JdbcType.NUMERIC),
        Result(column="sales_price_actual", property="salesPriceActual", jdbcType=JdbcType.NUMERIC),
        Result(column="sales_date", property="salesDate", jdbcType=JdbcType.DATE),
        Result(column="vending_machine_id", property="vendingMachineId", jdbcType=JdbcType.INTEGER)
    ])
    fun selectMany(selectStatement: SelectStatementProvider): List<VendingMachineSalesRecord>

    @UpdateProvider(type=SqlProviderAdapter::class, method="update")
    fun update(updateStatement: UpdateStatementProvider): Int

    @Select("""
        <script>
            SELECT
                t1.id,
                t1.sales_date as salesDate,
                t1.sales_price_on_display as salesPriceOnDisplay,
                t1.sales_price_actual as salesPriceActual,
                
                t2.id as vendingMachineId,
                t2.vending_machine_name as vendingMachineName,
                t2.vending_machine_code as vendingMachineCode,
                t2.address as address,
                t2.fee_rate as feeRate,
                t2.fee_fixed as feeFixed,
                t2.order_seq as vendingMachineOrderSeq
             
            FROM
                vending_machine_sales t1
                INNER JOIN vending_machine t2 on t1.vending_machine_id = t2.id
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
                <if test="vendingMachineCode != null and vendingMachineCode != ''">
                    AND t2.vending_machine_code like '%' || #{vendingMachineCode} || '%'
                </if>
                <if test="vendingMachineName != null and vendingMachineName != ''">
                    AND t2.vending_machine_name like '%' || #{vendingMachineName} || '%'
                </if>
                <if test="address != null and address != ''">
                    AND t2.address like '%' || #{address} || '%'
                </if>
            </where>
        </script>
    """)
    fun selectByVmFeeInquirySelectForm(form: VmFeeInquirySelectForm): List<VendingMachineSalesJoinedRecord>
}