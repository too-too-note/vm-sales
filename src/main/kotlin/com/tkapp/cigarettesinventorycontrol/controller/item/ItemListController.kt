package com.tkapp.cigarettesinventorycontrol.controller.item

import com.tkapp.cigarettesinventorycontrol.common.validation.DeleteGroup
import com.tkapp.cigarettesinventorycontrol.common.validation.InsertGroup
import com.tkapp.cigarettesinventorycontrol.common.validation.UpdateGroup
import com.tkapp.cigarettesinventorycontrol.model.data.ItemRecord
import com.tkapp.cigarettesinventorycontrol.service.item.ItemListService
import com.tkapp.cigarettesinventorycontrol.service.item.ItemListServiceImpl
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping

/**
 * 商品リスト
 */
@RestController
@RequestMapping("item-list")
class ItemListController(
        private val itemListService: ItemListService
) {

    /**
     * 検索して商品を返却
     */
    @PostMapping("/select")
    fun select(): List<ItemRecord> {
        return itemListService.select()
    }

    /**
     *　商品を追加
     */
    @PostMapping("/insert")
    fun insert(@RequestBody @Validated(InsertGroup::class) form: ItemListForm) {
        val item = ItemRecord(
                itemName = form.itemName,
                itemCode = form.itemCode,
                price = form.price,
                orderSeq = form.orderSeq,
                invalid = form.invalid
        )
        return itemListService.insert(item)
    }

    /**
     * 商品を更新
     */
    @PostMapping("/update")
    fun update(@RequestBody @Validated(UpdateGroup::class) form: ItemListForm) {
        val item = ItemRecord(
                id = form.id,
                itemName = form.itemName,
                itemCode = form.itemCode,
                price = form.price,
                orderSeq = form.orderSeq,
                invalid = form.invalid
        )
        return itemListService.update(item)
    }

    /**
     * 商品を削除(論理削除)
     */
    @PostMapping("/delete")
    fun delete(@RequestBody @Validated(DeleteGroup::class) form: ItemListForm) {
        return itemListService.delete(form.id)
    }
}