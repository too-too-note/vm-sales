package com.tkapp.cigarettesinventorycontrol.service.salesdownload

import com.tkapp.cigarettesinventorycontrol.controller.salesdownload.SalesDownloadForm
import com.tkapp.cigarettesinventorycontrol.controller.salesinquiry.SalesInquirySelectForm
import com.tkapp.cigarettesinventorycontrol.mapper.data.*
import com.tkapp.cigarettesinventorycontrol.mapper.data.ItemDynamicSqlSupport.Item.id
import com.tkapp.cigarettesinventorycontrol.mapper.data.ItemDynamicSqlSupport.Item.invalid
import com.tkapp.cigarettesinventorycontrol.mapper.data.ItemDynamicSqlSupport.Item.itemCode
import com.tkapp.cigarettesinventorycontrol.mapper.data.ItemDynamicSqlSupport.Item.orderSeq
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
class SalesDownloadServiceImpl(
        private val salesInquiryService: SalesInquiryServiceImpl,
        private val itemMapper: ItemMapper,
        private val vendingMachineMapper: VendingMachineMapper,
        private val vendingMachineSalesMapper: VendingMachineSalesMapper,
) : SalesDownloadService {

    /**
     * 売り上げエクセルを作成する
     */
    override fun createSalesExcel(form: SalesDownloadForm): ByteArray {
        // excel用オブジェクト作成
        val workBook = XSSFWorkbook()
        val sheet = workBook.createSheet()

        // 年を取得して追加
        val headerRow = sheet.createRow(0)
        val titleCell1 = headerRow.createCell(0)
        val yearFormat = SimpleDateFormat("yyyy")
        val year = if (form.fromDate != null) yearFormat.format(form.fromDate) else ""
        titleCell1.setCellValue(year.toString() + "年")

        // 月を取得して追加
        val titleCell2 = headerRow.createCell(2)
        val monthFormat = SimpleDateFormat("MM")
        val month = if (form.fromDate != null) monthFormat.format(form.fromDate) else ""
        titleCell2.setCellValue(month.toString() + "月")

        // fromDate ~ toDateを取得して追加
        val titleCell3 = headerRow.createCell(4)
        val monthFormat2 = SimpleDateFormat("yyyy-MM-dd")
        val month2 = monthFormat2.format(form.fromDate) + " ~ " + monthFormat2.format(form.toDate)
        titleCell3.setCellValue(month2)

        // 売り上げ表を取得する
        val salesTable = createSalesTable(form)

        // 売り上げ表をExcelにつめる
        val fm = workBook.createDataFormat()
        val cs = workBook.createCellStyle()
        cs.dataFormat = fm.getFormat("#,##0") //数値カンマ編集
        salesTable.forEachIndexed { index, list ->
            // createRowで列を指定
            val row = sheet.createRow(2 + index)

            list.forEachIndexed { index1, value ->
                // createCellで行を指定
                val cell = row.createCell(2 + index1)

                // 値を記入
                when (value) {
                    is String -> {
                        cell.setCellValue(value)
                    }
                    is Double -> {
                        cell.cellStyle = cs
                        cell.setCellValue(value.toDouble())
                    }
                    is BigDecimal -> {
                        cell.cellStyle = cs
                        cell.setCellValue(value.toDouble())
                    }
                    is Int -> {
                        cell.cellStyle = cs
                        cell.setCellValue(value.toDouble())
                    }
                }
            }
        }

        // バイナリ化して返却
        val bos = ByteArrayOutputStream()
        workBook.write(bos)
        return bos.toByteArray()
    }

    /**
     * 売り上げ表を作成する
     */
    private fun createSalesTable(form: SalesDownloadForm): List<List<Any>> {

        // 商品基準で売り上げリストを取得
        val salesListByItem = salesInquiryService.selectItemSales(SalesInquirySelectForm(
                toDate = form.toDate,
                fromDate = form.fromDate,
                searchByDateList = false
        ))

        // 全商品(無効を除く)を取得
        val items = itemMapper.select {
            where(invalid, isEqualTo(false))
            orderBy(orderSeq, itemCode, id)
        }

        // 全自販機(無効を除く)を取得
        val vms = vendingMachineMapper.select {
            where(VendingMachineDynamicSqlSupport.VendingMachine.invalid, isEqualTo(false))
            orderBy(VendingMachineDynamicSqlSupport.VendingMachine.orderSeq, VendingMachineDynamicSqlSupport.VendingMachine.vendingMachineCode, VendingMachineDynamicSqlSupport.VendingMachine.id)
        }

        // 表を作成
        val table = mutableListOf<MutableList<Any>>()

        // ヘッダーを作成
        val header = mutableListOf<Any>("銘柄", "単価")
        vms.forEach { header += it.vendingMachineName ?: "" }
        header += "合計数量"
        header += "合計金額"
        table += header

        // 自販機の売り上げ合計を格納しておく
        val vmSalesTotalMap = vms.associate { Pair(it.id, BigDecimal.ZERO) }.toMutableMap()

        // 全商品分ループ
        items.forEach { item ->

            // 最初の二列は商品名と価格
            val row = mutableListOf<Any>()
            row += item.itemName ?: ""
            row += item.price.toString()

            // 該当の商品の売り上げ情報を取得
            val itemSales = salesListByItem.itemSalesList.find { it.itemId == item.id }

            // 全自販機分ループ
            vms.forEach vmf@{ vm ->
                // 該当商品の売り上げ情報が無い場合は0
                if (itemSales == null) {
                    row += BigDecimal.ZERO
                    return@vmf
                }

                // 該当自販機の売り上げ情報が無い場合は0
                val vmSales = itemSales.salesList.filter { it.vendingMachineId == vm.id }
                if (vmSales.isEmpty()) {
                    row += BigDecimal.ZERO
                    return@vmf
                }

                // 該当自販機の売り上げ情報が存在した場合、合計値を算出
                val salesQuantity = vmSales.sumOf { it.salesQuantity ?: 0 }
                row += salesQuantity

                // 自販機の売り上げ金額を足して格納しておく
                val salesPrice = (item.price ?: BigDecimal.ZERO) * salesQuantity.toBigDecimal()
                vmSalesTotalMap[vm.id!!] = vmSalesTotalMap[vm.id!!]!! + salesPrice
            }

            // 数量合計、金額合計をそれぞれ入れる
            row += itemSales?.subtotalQuantity ?: BigDecimal.ZERO
            row += itemSales?.subtotalSales ?:  BigDecimal.ZERO
            table += row
        }

        // 集計行1(合計金額)を作成
        val agRow1 = mutableListOf<Any>("合計金額", "")
        vms.forEach {   // 格納しておいた自販機別の集計情報を入れていく
            agRow1 += vmSalesTotalMap.getOrDefault(it.id, BigDecimal.ZERO)
        }
        agRow1 += ""
        agRow1 += vmSalesTotalMap.map { it.value }.sumOf { it } // 総合計を入れる
        table += agRow1

        // 自販機売り上げ情報を取得
        val vendingMachineSalesList = vendingMachineSalesMapper.select {
            and(VendingMachineSalesDynamicSqlSupport.VendingMachineSales.salesDate, isGreaterThanOrEqualTo(form.fromDate!!))
            and(VendingMachineSalesDynamicSqlSupport.VendingMachineSales.salesDate, isLessThanOrEqualTo(form.toDate!!))
        }

        // 自販機IDごとにgroupBy
        val vmSalesListMap = vendingMachineSalesList.groupBy { it.vendingMachineId!! }

        // 集計行2(総売り上げ)を作成
        val agRow2 = mutableListOf<Any>("総売り上げ", "")
        vms.forEach {
            val salesMap = vmSalesListMap[it.id]
            if (salesMap == null) {
                agRow2 += BigDecimal.ZERO
                return@forEach
            }
            agRow2 += salesMap.sumOf { s -> s.salesPriceOnDisplay ?: BigDecimal.ZERO }
        }
        agRow2 += ""
        agRow2 += vendingMachineSalesList.sumOf { s -> s.salesPriceOnDisplay ?: BigDecimal.ZERO }
        table += agRow2

        // 集計行3(入金金額)を作成
        val agRow3 = mutableListOf<Any>("入金金額", "")
        vms.forEach {
            val salesMap = vmSalesListMap[it.id]
            if (salesMap == null) {
                agRow3 += BigDecimal.ZERO
                return@forEach
            }
            agRow3 += salesMap.sumOf { s -> s.salesPriceActual ?: BigDecimal.ZERO }
        }
        agRow3 += ""
        agRow3 += vendingMachineSalesList.sumOf { s -> s.salesPriceActual ?: BigDecimal.ZERO }
        table += agRow3

        // 集計行4(手数料)を作成
        val agRow4 = mutableListOf<Any>("手数料", "")
        var total = BigDecimal.ZERO
        vms.forEach {
            val fee = if (it.feeRate != null) {
                val salesMap = vmSalesListMap[it.id]
                if (salesMap == null) {
                    BigDecimal.ZERO
                } else {
                    salesMap.sumOf { s -> s.salesPriceOnDisplay ?: BigDecimal.ZERO } * it.feeRate!!
                }
            } else {
                it.feeFixed ?: BigDecimal.ZERO
            }.setScale(0, RoundingMode.HALF_UP)
            total += fee
            agRow4 += fee
        }
        agRow4 += ""
        agRow4 += total
        table += agRow4

        return table
    }
}