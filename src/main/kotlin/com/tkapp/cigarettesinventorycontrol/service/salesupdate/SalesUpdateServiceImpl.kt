package com.tkapp.cigarettesinventorycontrol.service.salesupdate

import com.tkapp.cigarettesinventorycontrol.controller.salesinquiry.SalesInquirySelectForm
import com.tkapp.cigarettesinventorycontrol.controller.salesupdate.SalesUpdateForm
import com.tkapp.cigarettesinventorycontrol.mapper.data.*
import com.tkapp.cigarettesinventorycontrol.mapper.data.VendingMachineItemSalesDynamicSqlSupport.VendingMachineItemSales.id
import com.tkapp.cigarettesinventorycontrol.mapper.data.VendingMachineItemSalesDynamicSqlSupport.VendingMachineItemSales.salesQuantity
import com.tkapp.cigarettesinventorycontrol.mapper.data.VendingMachineSalesDynamicSqlSupport.VendingMachineSales.salesDate
import com.tkapp.cigarettesinventorycontrol.model.data.VendingMachineItemSalesJoinedRecord
import com.tkapp.cigarettesinventorycontrol.model.data.VendingMachineSalesRecord
import com.tkapp.cigarettesinventorycontrol.service.salesinquiry.itemout.ItemSalesByItemOut
import com.tkapp.cigarettesinventorycontrol.service.salesinquiry.itemout.SalesInquiryItemOut
import com.tkapp.cigarettesinventorycontrol.service.salesinquiry.itemout.VmSalesByItemOut
import com.tkapp.cigarettesinventorycontrol.service.salesinquiry.vmout.ItemSalesByVmOut
import com.tkapp.cigarettesinventorycontrol.service.salesinquiry.vmout.SalesInquiryVmOut
import com.tkapp.cigarettesinventorycontrol.service.salesinquiry.vmout.VmSalesByVmOut
import org.mybatis.dynamic.sql.util.kotlin.elements.isEqualTo
import org.mybatis.dynamic.sql.util.kotlin.elements.isGreaterThanOrEqualTo
import org.mybatis.dynamic.sql.util.kotlin.elements.isIn
import org.mybatis.dynamic.sql.util.kotlin.elements.isLessThanOrEqualTo
import org.springframework.stereotype.Service
import java.math.BigDecimal

@Service
class SalesUpdateServiceImpl(
        private val vendingMachineItemSalesMapper: VendingMachineItemSalesMapper,
        private val vendingMachineSalesMapper: VendingMachineSalesMapper,
) : SalesUpdateService {

    override fun updateSales(form: SalesUpdateForm) {
        form.vmItemSalesList.forEach {
            if (it.salesQuantity == null) return@forEach
            vendingMachineItemSalesMapper.update {
                set(salesQuantity).equalTo(it.salesQuantity)
                and(id, isEqualTo(it.vendingMachineItemSalesId))
            }
        }
    }
}