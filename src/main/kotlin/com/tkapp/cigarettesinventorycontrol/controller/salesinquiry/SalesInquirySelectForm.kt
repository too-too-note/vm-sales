/*
 * Auto-generated file. Created by MyBatis Generator
 * Generation date: 2021-06-09T20:53:50.103566+09:00
 */
package com.tkapp.cigarettesinventorycontrol.controller.salesinquiry

import java.util.*

/**
 * 売り上げデータ照会検索フォーム
 */
data class SalesInquirySelectForm(
        var toDate: Date? = null,
        var fromDate: Date? = null,
        var itemCode: String? = null,
        var itemName: String? = null,
        var vendingMachineName: String? = null,
        var vendingMachineCode: String? = null,
        var address: String? = null,
        var dateList: List<Date> = listOf(),
        var searchByDateList: Boolean = false,
)