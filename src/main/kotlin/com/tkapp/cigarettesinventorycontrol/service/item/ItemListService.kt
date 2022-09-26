package com.tkapp.cigarettesinventorycontrol.service.item

import com.tkapp.cigarettesinventorycontrol.model.data.ItemRecord

interface ItemListService {
    fun select(): List<ItemRecord>
    fun insert(itemRecord: ItemRecord)
    fun update(itemRecord: ItemRecord)
    fun delete(itemId: Int?)
}