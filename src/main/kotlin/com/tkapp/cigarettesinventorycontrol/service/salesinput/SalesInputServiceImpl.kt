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
        private val vendingMachineItemSalesMapper: VendingMachineItemSalesMapper,
        private val vendingMachineSalesMapper: VendingMachineSalesMapper,
) : SalesInputService {

    /**
     * 売上確定処理
     */
    @Transactional
    override fun determine(form: SalesInputForm) {
        // 自販機売り上げ情報の取得
        val vmSales = vendingMachineSalesMapper.selectOne {
            and(vendingMachineId, isEqualTo(form.vendingMachineId!!))
            and(salesDate, isEqualTo(form.salesDate!!))
        }

        if (vmSales == null) {
            // 売上情報がnullの場合は自販機売上情報をinsert
            vendingMachineSalesMapper.insert(VendingMachineSalesRecord(
                    salesPriceActual = form.salesPriceActual,
                    salesPriceOnDisplay = form.salesPriceOnDisplay,
                    salesDate = form.salesDate,
                    vendingMachineId = form.vendingMachineId
            ))
        } else {
            // 売上情報が存在していた場合は更新
            vmSales.salesPriceActual = form.salesPriceActual
            vmSales.salesPriceOnDisplay = form.salesPriceOnDisplay
            vendingMachineSalesMapper.updateByPrimaryKey(vmSales)
        }

        // 商品売上リスト分ループ
        form.itemSalesList.forEach {
            if (it.update) {    // 更新フラグがtrueの場合は更新
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
            } else {    // 更新フラグがfalseの場合は追加
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

    /**
     * 商品IDと売上情報をMap化して返却
     */
    override fun getMapVendingMachineSalesIdToSales(form: SalesInputSelectForm): Map<Int, Int> {
        val vmItemSales = vendingMachineItemSalesMapper.select {
            and(salesDate, isEqualTo(form.salesDate!!))
            and(vendingMachineId, isEqualTo(form.vendingMachineId!!))
        }
        return vmItemSales.associate { Pair(it.itemId ?: 0, it.salesQuantity ?: 0) }
    }

    /**
     * 自販機売上情報の取得
     */
    override fun getVendingMachineSales(form: SalesInputSelectForm): VendingMachineSalesRecord? {
        return vendingMachineSalesMapper.selectOne {
            and(vendingMachineId, isEqualTo(form.vendingMachineId!!))
            and(salesDate, isEqualTo(form.salesDate!!))
        }
    }

    /**
     * 売上情報の削除
     */
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