/*
 * Auto-generated file. Created by MyBatis Generator
 * Generation date: 2021-06-09T20:53:50.103566+09:00
 */
package com.tkapp.cigarettesinventorycontrol.controller.vmfeeinquiry

import com.tkapp.cigarettesinventorycontrol.model.data.AddressBookRecord
import java.math.BigDecimal
import java.math.BigDecimal.ROUND_HALF_UP
import java.math.RoundingMode
import java.util.*

data class VmFeeInquiryOut(
        var vendingMachineList: List<VendingMachine> = listOf(),

        var companyInfo: AddressBookRecord

) {
        val totalFee: BigDecimal
                get() = vendingMachineList.map { it.calculatedFee }.sumOf { it }

        class VendingMachine(
                var vendingMachineId: Int? = null,
                var vendingMachineName: String? = null,
                var vendingMachineCode: String? = null,
                var address: String? = null,
                var feeRate: BigDecimal? = null,
                var feeFixed: BigDecimal? = null,
                var salesList: List<VendingMachineSales>
        ) {
                val totalSalesPriceOnDisplay: BigDecimal
                        get() = salesList.map { it.salesPriceOnDisplay ?: BigDecimal.ZERO }.sumOf { it }

                val calculatedFee: BigDecimal
                        get() {
                                val value = if (feeRate != null) totalSalesPriceOnDisplay * feeRate!! else feeFixed ?: BigDecimal.ZERO
                                return value.setScale(0, RoundingMode.HALF_UP);
                        }

                class VendingMachineSales(
                        var salesDate: Date? = null,
                        var salesPriceOnDisplay: BigDecimal? = null,
                        var salesPriceActual: BigDecimal? = null,
                )
        }
}