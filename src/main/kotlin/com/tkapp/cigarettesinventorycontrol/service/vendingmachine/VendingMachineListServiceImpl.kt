package com.tkapp.cigarettesinventorycontrol.service.vendingmachine

import com.tkapp.cigarettesinventorycontrol.mapper.data.*
import com.tkapp.cigarettesinventorycontrol.mapper.data.VendingMachineDynamicSqlSupport.VendingMachine.id
import com.tkapp.cigarettesinventorycontrol.mapper.data.VendingMachineDynamicSqlSupport.VendingMachine.orderSeq
import com.tkapp.cigarettesinventorycontrol.mapper.data.VendingMachineDynamicSqlSupport.VendingMachine.vendingMachineCode
import com.tkapp.cigarettesinventorycontrol.model.data.VendingMachineRecord
import org.springframework.stereotype.Service
import java.lang.Exception

@Service
class VendingMachineListServiceImpl(
        private val vendingMachineMapper: VendingMachineMapper
) : VendingMachineListService {
    override fun select(): List<VendingMachineRecord> {
        return vendingMachineMapper.select {
            orderBy(orderSeq, vendingMachineCode, id)
        }
    }

    override fun insert(vendingMachineRecord: VendingMachineRecord) {
        vendingMachineMapper.insert(vendingMachineRecord)
    }

    override fun update(vendingMachineRecord: VendingMachineRecord) {
        vendingMachineMapper.updateByPrimaryKey(vendingMachineRecord)
    }

    override fun delete(vendingMachineId: Int?) {
        if (vendingMachineId == null) throw Exception()
        val vm = vendingMachineMapper.selectByPrimaryKey(vendingMachineId) ?: throw Exception()
        vm.invalid = true
        vendingMachineMapper.updateByPrimaryKey(vm)
    }
}