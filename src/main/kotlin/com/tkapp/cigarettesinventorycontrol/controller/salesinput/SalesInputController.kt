package com.tkapp.cigarettesinventorycontrol.controller.salesinput

import com.tkapp.cigarettesinventorycontrol.model.data.ItemRecord
import com.tkapp.cigarettesinventorycontrol.model.data.VendingMachineRecord
import com.tkapp.cigarettesinventorycontrol.service.item.ItemListService
import com.tkapp.cigarettesinventorycontrol.service.salesinput.SalesInputService
import com.tkapp.cigarettesinventorycontrol.service.vendingmachine.VendingMachineListService
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping

/**
 * 売り上げ入力
 */
@RestController
@RequestMapping("sales")
class SalesInputController(
        private val itemListService: ItemListService,
        private val salesInputService: SalesInputService,
        private val vendingMachineListService: VendingMachineListService
) {
    /**
     * 商品一覧取得
     */
    @PostMapping("/selectItems")
    fun selectItems(@RequestBody form: SalesInputItemSelectForm): List<ItemRecord> {
        val items = itemListService.select()

        // 無効レコードを含めない場合はフィルターして返却
        return if (form.includeInvalid) {
            items
        } else {
            items.filter { !it.invalid }
        }
    }

    /**
     * 自販機一覧取得
     */
    @PostMapping("/selectVendingMachines")
    fun selectVendingMachines(@RequestBody form: SalesInputItemSelectForm): List<VendingMachineRecord> {
        return vendingMachineListService.select().filter { !it.invalid }
    }

    /**
     * 自販機の商品ごとの売り上げデータの取得
     */
    @PostMapping("/getVendingMachineItemSales")
    fun getVendingMachineItemSales(@RequestBody @Validated form: SalesInputSelectForm): SalesInputOut {
        val vmSales = salesInputService.getVendingMachineSales(form)
        return SalesInputOut().apply {
            salesPriceActual = vmSales?.salesPriceActual
            salesPriceOnDisplay = vmSales?.salesPriceOnDisplay
            itemIdToSalesMap = salesInputService.getMapVendingMachineSalesIdToSales(form)
        }
    }

    /**
     * 売り上げ確定
     */
    @PostMapping("/determine")
    fun determine(@RequestBody @Validated form: SalesInputForm) {
        salesInputService.determine(form)
    }

    /**
     * 売り上げデータ削除
     */
    @PostMapping("/deleteSales")
    fun deleteSales(@RequestBody @Validated form: SalesInputDeleteForm) {
        salesInputService.deleteSales(form)
    }
}