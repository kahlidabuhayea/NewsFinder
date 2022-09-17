package com.app.newsfinder.presentation.utils

import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*


fun String.formatDate(): String {
    val firstFormatFrom = SimpleDateFormat("yyyy-MM-dd hh:mm:ss", Locale.ENGLISH)
    val secondFormatFrom = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.ENGLISH)
    val formatTo = SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)
    return try {
        firstFormatFrom.parse(this)?.let { formatTo.format(it) } ?: ""
    } catch (e: ParseException) {
        try {
            secondFormatFrom.parse(this)?.let { formatTo.format(it) } ?: ""
        } catch (e: ParseException) {
            this
        }
    }
}
