package com.tkapp.cigarettesinventorycontrol.service.vmfeeinquiry

import com.tkapp.cigarettesinventorycontrol.controller.vmfeeinquiry.VmFeeInquiryOut
import com.tkapp.cigarettesinventorycontrol.controller.vmfeeinquiry.VmFeeInquirySelectForm
import com.tkapp.cigarettesinventorycontrol.mapper.data.*
import com.tkapp.cigarettesinventorycontrol.model.data.VendingMachineSalesJoinedRecord
import org.springframework.stereotype.Service

@Service
class VmFeeInquiryServiceImpl(
        private val vendingMachineSalesMapper: VendingMachineSalesMapper,
        private val addressBookMapper: AddressBookMapper
) : VmFeeInquiryService {
    override fun select(form: VmFeeInquirySelectForm): VmFeeInquiryOut {
        val companyInfo = addressBookMapper.selectByPrimaryKey("C001") ?: throw Exception()

        val vmSales = vendingMachineSalesMapper.selectByVmFeeInquirySelectForm(form)
        val salesMap = vmSales.groupBy { it.vendingMachineId }

        val out = VmFeeInquiryOut(companyInfo = companyInfo)
        out.vendingMachineList = salesMap.map { salesMap ->
            val vm = salesMap.value[0]
            val vmSalesList = salesMap.value.map { VmFeeInquiryOut.VendingMachine.VendingMachineSales(it.salesDate, it.salesPriceOnDisplay, it.salesPriceActual) }

            VmFeeInquiryOut.VendingMachine(
                    vendingMachineId = salesMap.key,
                    vendingMachineName = vm.vendingMachineName,
                    vendingMachineCode = vm.vendingMachineCode,
                    address = vm.address,
                    feeRate = vm.feeRate,
                    feeFixed = vm.feeFixed,
                    salesList = vmSalesList
            )
        }
        return out
    }
}