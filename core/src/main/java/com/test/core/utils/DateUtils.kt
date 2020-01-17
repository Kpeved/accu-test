package com.test.core.utils

import org.threeten.bp.LocalDate
import org.threeten.bp.format.DateTimeFormatter

object DateUtils {

    fun formatFrom_yyyy_MM_dd_To_dd_MMM(serverFormattedTime: String) =
        LocalDate.parse(serverFormattedTime, DateTimeFormatter.ofPattern(DEFAULT_DATE_TIME_SERVER_FORMAT))
            .format(DateTimeFormatter.ofPattern(DATE_FORMAT_dd_MMM))

    const val DEFAULT_DATE_TIME_SERVER_FORMAT = "yyyy-MM-dd'T'HH:mm:ssXXX"
    const val DATE_FORMAT_dd_MMM = "dd MMM"
}