/*
 * Auto-generated file. Created by MyBatis Generator
 * Generation date: 2021-06-09T20:53:50.103566+09:00
 */
package com.tkapp.cigarettesinventorycontrol.controller.vmfeeinquiry

import java.util.*

data class VmFeeInquirySelectForm(
        var toDate: Date? = null,
        var fromDate: Date? = null,
        var vendingMachineName: String? = null,
        var vendingMachineCode: String? = null,
        var address: String? = null,
        var dateList: List<Date> = listOf(),
        var searchByDateList: Boolean = false,
)