package com.tkapp.cigarettesinventorycontrol.service.salesdatetrans

import com.tkapp.cigarettesinventorycontrol.controller.salesdatatrans.SalesDataTransForm
import com.tkapp.cigarettesinventorycontrol.controller.salesdownload.SalesDownloadForm
import com.tkapp.cigarettesinventorycontrol.controller.salesinquiry.SalesInquirySelectForm
import com.tkapp.cigarettesinventorycontrol.mapper.data.*
import com.tkapp.cigarettesinventorycontrol.mapper.data.ItemDynamicSqlSupport.Item.id
import com.tkapp.cigarettesinventorycontrol.mapper.data.ItemDynamicSqlSupport.Item.invalid
import com.tkapp.cigarettesinventorycontrol.mapper.data.ItemDynamicSqlSupport.Item.itemCode
import com.tkapp.cigarettesinventorycontrol.mapper.data.ItemDynamicSqlSupport.Item.orderSeq
import com.tkapp.cigarettesinventorycontrol.mapper.data.VendingMachineSalesDynamicSqlSupport.VendingMachineSales.salesDate
import com.tkapp.cigarettesinventorycontrol.mapper.data.VendingMachineSalesDynamicSqlSupport.VendingMachineSales.vendingMachineId
import com.tkapp.cigarettesinventorycontrol.service.salesinquiry.SalesInquiryServiceImpl
import org.apache.poi.xssf.usermodel.XSSFWorkbook
import org.mybatis.dynamic.sql.util.kotlin.elements.isEqualTo
import org.mybatis.dynamic.sql.util.kotlin.elements.isGreaterThanOrEqualTo
import org.mybatis.dynamic.sql.util.kotlin.elements.isIn
import org.mybatis.dynamic.sql.util.kotlin.elements.isLessThanOrEqualTo
import org.springframework.stereotype.Service
import java.io.ByteArrayOutputStream
import java.math.BigDecimal
import java.math.RoundingMode
import java.text.SimpleDateFormat


@Service
class SalesDataTransServiceImpl(
        private val vendingMachineSalesMapper: VendingMachineSalesMapper,
        private val vendingMachineItemSalesMapper: VendingMachineItemSalesMapper,
) : SalesDataTransService {

    /**
     * 売り上げデータを振り返る
     */
    override fun translateSalesData(form: SalesDataTransForm) {

        // 自販機売り上げ情報の取得
        val vmSales = vendingMachineSalesMapper.select {
            and(vendingMachineId, isEqualTo(form.vendingMachineId!!))
            and(salesDate, isEqualTo(form.toDate!!))
        }

        // 取得できない場合はエラー
        if (vmSales.isNotEmpty()) {
            throw Exception()
        }

        // 自販機売り上げ情報の更新
        vendingMachineSalesMapper.update {
            set(salesDate).equalTo(form.toDate!!)
            and(vendingMachineId, isEqualTo(form.vendingMachineId!!))
            and(salesDate, isEqualTo(form.fromDate!!))
        }

        // 自販機商品売り上げ情報の更新
        vendingMachineItemSalesMapper.update {
            set(salesDate).equalTo(form.toDate!!)
            and(vendingMachineId, isEqualTo(form.vendingMachineId!!))
            and(salesDate, isEqualTo(form.fromDate!!))
        }
    }
}