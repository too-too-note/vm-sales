/*
 * Auto-generated file. Created by MyBatis Generator
 * Generation date: 2021-06-09T20:53:50.103566+09:00
 */
package com.tkapp.cigarettesinventorycontrol.controller.salesdownload

import java.math.BigDecimal
import java.util.*
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull

/**
 * 売上データダウンロード入力フォーム
 */
data class SalesDownloadForm(
        /**
         * to 日付
         */
        var toDate: Date? = null,

        /**
         * from 日付
         */
        var fromDate: Date? = null,
)