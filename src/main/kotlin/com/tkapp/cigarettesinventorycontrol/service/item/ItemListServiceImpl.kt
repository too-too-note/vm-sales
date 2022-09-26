package com.tkapp.cigarettesinventorycontrol.service.item

import com.tkapp.cigarettesinventorycontrol.mapper.data.*
import com.tkapp.cigarettesinventorycontrol.mapper.data.ItemDynamicSqlSupport.Item.id
import com.tkapp.cigarettesinventorycontrol.mapper.data.ItemDynamicSqlSupport.Item.itemCode
import com.tkapp.cigarettesinventorycontrol.mapper.data.ItemDynamicSqlSupport.Item.itemName
import com.tkapp.cigarettesinventorycontrol.mapper.data.ItemDynamicSqlSupport.Item.orderSeq
import com.tkapp.cigarettesinventorycontrol.model.data.ItemRecord
import org.mybatis.dynamic.sql.util.kotlin.elements.isLike
import org.springframework.stereotype.Service

@Service
class ItemListServiceImpl(
        private val itemMapper: ItemMapper
) : ItemListService {
    override fun select(): List<ItemRecord> {
        return itemMapper.select {
            orderBy(orderSeq, itemCode, id)
        }
    }

    override fun insert(itemRecord: ItemRecord) {
        itemMapper.insert(itemRecord)
    }

    override fun update(itemRecord: ItemRecord) {
        itemMapper.updateByPrimaryKey(itemRecord)
    }

    override fun delete(itemId: Int?) {
        if (itemId == null) throw Exception()
        val item = itemMapper.selectByPrimaryKey(itemId) ?: throw Exception()
        item.invalid = true
        itemMapper.updateByPrimaryKey(item)
    }
}