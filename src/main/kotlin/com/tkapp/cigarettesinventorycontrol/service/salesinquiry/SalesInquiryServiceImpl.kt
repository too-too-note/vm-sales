package com.tkapp.cigarettesinventorycontrol.service.salesinquiry

import com.tkapp.cigarettesinventorycontrol.controller.salesinquiry.SalesInquirySelectForm
import com.tkapp.cigarettesinventorycontrol.mapper.data.*
import com.tkapp.cigarettesinventorycontrol.mapper.data.VendingMachineSalesDynamicSqlSupport.VendingMachineSales.salesDate
import com.tkapp.cigarettesinventorycontrol.model.data.VendingMachineItemSalesJoinedRecord
import com.tkapp.cigarettesinventorycontrol.model.data.VendingMachineSalesRecord
import com.tkapp.cigarettesinventorycontrol.service.salesinquiry.itemout.ItemSalesByItemOut
import com.tkapp.cigarettesinventorycontrol.service.salesinquiry.itemout.SalesInquiryItemOut
import com.tkapp.cigarettesinventorycontrol.service.salesinquiry.itemout.VmSalesByItemOut
import com.tkapp.cigarettesinventorycontrol.service.salesinquiry.vmout.ItemSalesByVmOut
import com.tkapp.cigarettesinventorycontrol.service.salesinquiry.vmout.SalesInquiryVmOut
import com.tkapp.cigarettesinventorycontrol.service.salesinquiry.vmout.VmSalesByVmOut
import org.mybatis.dynamic.sql.util.kotlin.elements.isGreaterThanOrEqualTo
import org.mybatis.dynamic.sql.util.kotlin.elements.isIn
import org.mybatis.dynamic.sql.util.kotlin.elements.isLessThanOrEqualTo
import org.springframework.stereotype.Service
import java.math.BigDecimal

@Service
class SalesInquiryServiceImpl(
        private val vendingMachineItemSalesMapper: VendingMachineItemSalesMapper,
        private val vendingMachineSalesMapper: VendingMachineSalesMapper,
) : SalesInquiryService {
    override fun selectItemSales(form: SalesInquirySelectForm): SalesInquiryItemOut {
        val vmItemSalesList = vendingMachineItemSalesMapper.selectBySalesInquirySelectForm(form)

        val salesMap = vmItemSalesList.groupBy { it.itemId!! }

        val sales = salesMap.map { (itemId, salesList) ->
            val sortedSalesList = salesList.sortedWith(
                    compareBy<VendingMachineItemSalesJoinedRecord> { it.salesDate }
                            .thenBy { it.vendingMachineOrderSeq }
                            .thenBy { it.vendingMachineCode })

            val item = sortedSalesList[0]
            return@map ItemSalesByItemOut(itemId, item.itemCode, item.itemName, item.price, item.itemOrderSeq).apply {
                this.salesList = sortedSalesList.map {
                    VmSalesByItemOut(it.id, it.vendingMachineId, it.vendingMachineName, it.vendingMachineCode, it.salesQuantity, it.salesDate)
                }
            }
        }.sortedBy { it.orderSeq }

        val vmSales = selectVendingMachineSales(form)

        return SalesInquiryItemOut().apply {
            totalSalesPriceActual = vmSales.map { it.salesPriceActual }.sumOf { it ?: BigDecimal.ZERO }
            totalSalesPriceOnDisplay = vmSales.map { it.salesPriceOnDisplay }.sumOf { it ?: BigDecimal.ZERO }
            itemSalesList = sales
        }

    }

    override fun selectVmSales(form: SalesInquirySelectForm): SalesInquiryVmOut {
        val vmItemSalesList = vendingMachineItemSalesMapper.selectBySalesInquirySelectForm(form)

        val salesMap = vmItemSalesList.groupBy { it.vendingMachineId!! }

        val vmSales = selectVendingMachineSales(form)

        val vmSalesList = salesMap.map { (vendingMachineId, salesList) ->
            val sortedSalesList = salesList.sortedWith(
                    compareBy<VendingMachineItemSalesJoinedRecord> { it.salesDate }
                            .thenBy { it.itemOrderSeq }
                            .thenBy { it.itemCode })

            val vm = sortedSalesList[0]
            val sales = vmSales.filter { it.vendingMachineId == vendingMachineId }
            return@map VmSalesByVmOut(vendingMachineId, vm.vendingMachineName, vm.vendingMachineCode, vm.address, vm.vendingMachineOrderSeq).apply {
                subtotalSalesPriceActual = sales.map { it.salesPriceActual }.sumOf { it ?: BigDecimal.ZERO }
                subtotalSalesPriceOnDisplay = sales.map { it.salesPriceOnDisplay }.sumOf { it ?: BigDecimal.ZERO }
                this.itemList = sortedSalesList.map {
                    ItemSalesByVmOut(it.id, it.itemId, it.itemCode, it.itemName, it.price, it.salesQuantity, it.salesDate, it.itemOrderSeq)
                }
            }
        }.sortedBy { it.orderSeq }

        return SalesInquiryVmOut().apply {
            totalSalesPriceActual = vmSales.map { it.salesPriceActual }.sumOf { it ?: BigDecimal.ZERO }
            totalSalesPriceOnDisplay = vmSales.map { it.salesPriceOnDisplay }.sumOf { it ?: BigDecimal.ZERO }
            this.vmSalesList = vmSalesList
        }
    }

    private fun selectVendingMachineSales(form: SalesInquirySelectForm): List<VendingMachineSalesRecord> {
        return vendingMachineSalesMapper.select {
            if (form.searchByDateList) {
                and(salesDate, isIn(form.dateList))
            } else {
                and(salesDate, isGreaterThanOrEqualTo(form.fromDate!!))
                and(salesDate, isLessThanOrEqualTo(form.toDate!!))
            }
        }
    }
}