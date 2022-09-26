package com.tkapp.cigarettesinventorycontrol.controller.vendingmachine

import com.tkapp.cigarettesinventorycontrol.common.validation.DeleteGroup
import com.tkapp.cigarettesinventorycontrol.common.validation.InsertGroup
import com.tkapp.cigarettesinventorycontrol.common.validation.UpdateGroup
import com.tkapp.cigarettesinventorycontrol.controller.item.ItemListForm
import com.tkapp.cigarettesinventorycontrol.model.data.VendingMachineRecord
import com.tkapp.cigarettesinventorycontrol.service.vendingmachine.VendingMachineListService
import com.tkapp.cigarettesinventorycontrol.service.vendingmachine.VendingMachineListServiceImpl
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import java.math.BigDecimal

@RestController
@RequestMapping("vending-machine-list")
class VendingMachineListController(
        private val vendingMachineListService: VendingMachineListService
) {
    @PostMapping("/select")
    fun select(): List<VendingMachineRecord> {
        return vendingMachineListService.select()
    }

    @PostMapping("/insert")
    fun insert(@RequestBody @Validated(InsertGroup::class) form: VendingMachineListForm) {
        val feeRate = if (form.feeRate == BigDecimal.ZERO) null else form.feeRate
        val feeFixed = if (form.feeFixed == BigDecimal.ZERO) null else form.feeFixed

        checkFee(feeRate, feeFixed)

        val vendingMachineRecord = VendingMachineRecord(
                vendingMachineName = form.vendingMachineName,
                address = form.address,
                vendingMachineCode = form.vendingMachineCode,
                invalid = form.invalid,
                feeRate = feeRate,
                feeFixed = feeFixed,
                orderSeq = form.orderSeq,
        )
        return vendingMachineListService.insert(vendingMachineRecord)
    }

    @PostMapping("/update")
    fun update(@RequestBody @Validated(UpdateGroup::class) form: VendingMachineListForm) {
        val feeRate = if (form.feeRate == BigDecimal.ZERO) null else form.feeRate
        val feeFixed = if (form.feeFixed == BigDecimal.ZERO) null else form.feeFixed

        checkFee(feeRate, feeFixed)

        val vendingMachineRecord = VendingMachineRecord(
                id = form.id,
                vendingMachineName = form.vendingMachineName,
                address = form.address,
                vendingMachineCode = form.vendingMachineCode,
                orderSeq = form.orderSeq,
                invalid = form.invalid,
                feeRate = feeRate,
                feeFixed = feeFixed,
        )
        vendingMachineListService.update(vendingMachineRecord)
    }

    private fun checkFee(feeRate: BigDecimal?, feeFixed: BigDecimal?) {
        if (feeRate != null && feeFixed != null) throw Exception()
    }

    @PostMapping("/delete")
    fun delete(@RequestBody @Validated(DeleteGroup::class) form: VendingMachineListForm) {
        return vendingMachineListService.delete(form.id)
    }
}