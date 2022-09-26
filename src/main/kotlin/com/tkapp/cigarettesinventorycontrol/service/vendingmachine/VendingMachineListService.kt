package com.tkapp.cigarettesinventorycontrol.service.vendingmachine

import com.tkapp.cigarettesinventorycontrol.model.data.VendingMachineRecord

interface VendingMachineListService {
    fun select(): List<VendingMachineRecord>
    fun insert(vendingMachineRecord: VendingMachineRecord)
    fun update(vendingMachineRecord: VendingMachineRecord)
    fun delete(vendingMachineId: Int?)
}