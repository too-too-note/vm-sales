package com.tkapp.cigarettesinventorycontrol.service.salesinput

import com.tkapp.cigarettesinventorycontrol.controller.salesinput.SalesInputDeleteForm
import com.tkapp.cigarettesinventorycontrol.controller.salesinput.SalesInputForm
import com.tkapp.cigarettesinventorycontrol.controller.salesinput.SalesInputSelectForm
import com.tkapp.cigarettesinventorycontrol.mapper.data.*
import com.tkapp.cigarettesinventorycontrol.mapper.data.VendingMachineItemSalesDynamicSqlSupport.VendingMachineItemSales.itemId
import com.tkapp.cigarettesinventorycontrol.mapper.data.VendingMachineItemSalesDynamicSqlSupport.VendingMachineItemSales.salesDate
import com.tkapp.cigarettesinventorycontrol.mapper.data.VendingMachineItemSalesDynamicSqlSupport.VendingMachineItemSales.salesQuantity
import com.tkapp.cigarettesinventorycontrol.mapper.data.VendingMachineSalesDynamicSqlSupport.VendingMachineSales.vendingMachineId
import com.tkapp.cigarettesinventorycontrol.model.data.VendingMachineItemSalesRecord
import com.tkapp.cigarettesinventorycontrol.model.data.VendingMachineSalesRecord
import org.mybatis.dynamic.sql.util.kotlin.elements.isEqualTo
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class SalesInputServiceImpl(
        private val itemMapper: ItemMapper,
        private val vendingMachineItemSalesMapper: VendingMachineItemSalesMapper,
        private val vendingMachineSalesMapper: VendingMachineSalesMapper,
) : SalesInputService {

    @Transactional
    override fun determine(form: SalesInputForm) {
        val vmSales = vendingMachineSalesMapper.selectOne {
            and(vendingMachineId, isEqualTo(form.vendingMachineId!!))
            and(salesDate, isEqualTo(form.salesDate!!))
        }

        if (vmSales == null) {
            vendingMachineSalesMapper.insert(VendingMachineSalesRecord(
                    salesPriceActual = form.salesPriceActual,
                    salesPriceOnDisplay = form.salesPriceOnDisplay,
                    salesDate = form.salesDate,
                    vendingMachineId = form.vendingMachineId
            ))
        } else {
            vmSales.salesPriceActual = form.salesPriceActual
            vmSales.salesPriceOnDisplay = form.salesPriceOnDisplay
            vendingMachineSalesMapper.updateByPrimaryKey(vmSales)
        }

        form.itemSalesList.forEach {

            if (it.update) {
                if (it.salesQuantity == null) {
                    vendingMachineItemSalesMapper.delete {
                        and(salesDate, isEqualTo(form.salesDate!!))
                        and(vendingMachineId, isEqualTo(form.vendingMachineId!!))
                        and(itemId, isEqualTo(it.itemId))
                    }
                } else {
                    vendingMachineItemSalesMapper.update {
                        set(salesQuantity).equalTo(it.salesQuantity)

                        and(salesDate, isEqualTo(form.salesDate!!))
                        and(vendingMachineId, isEqualTo(form.vendingMachineId!!))
                        and(itemId, isEqualTo(it.itemId))
                    }
                }
            } else {
                if (it.salesQuantity == null) {
                    return@forEach
                }

                val ent = VendingMachineItemSalesRecord().apply {
                    salesDate = form.salesDate
                    salesQuantity = it.salesQuantity
                    vendingMachineId = form.vendingMachineId
                    itemId = it.itemId
                }
                vendingMachineItemSalesMapper.insert(ent)
            }
        }
    }

    override fun getMapVendingMachineSalesIdToSales(form: SalesInputSelectForm): Map<Int, Int> {
        val sales = vendingMachineItemSalesMapper.select {
            and(salesDate, isEqualTo(form.salesDate!!))
            and(vendingMachineId, isEqualTo(form.vendingMachineId!!))
        }
        return sales.associate { Pair(it.itemId ?: 0, it.salesQuantity ?: 0) }
    }

    override fun getVendingMachineSales(form: SalesInputSelectForm): VendingMachineSalesRecord? {
        return vendingMachineSalesMapper.selectOne {
            and(vendingMachineId, isEqualTo(form.vendingMachineId!!))
            and(salesDate, isEqualTo(form.salesDate!!))
        }
    }

    @Transactional
    override fun deleteSales(form: SalesInputDeleteForm) {
        vendingMachineSalesMapper.delete {
            and(salesDate, isEqualTo(form.salesDate!!))
            and(vendingMachineId, isEqualTo(form.vendingMachineId!!))
        }

        vendingMachineItemSalesMapper.delete {
            and(salesDate, isEqualTo(form.salesDate!!))
            and(vendingMachineId, isEqualTo(form.vendingMachineId!!))
        }
    }
}