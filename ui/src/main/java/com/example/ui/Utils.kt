package com.example.ui

import java.text.SimpleDateFormat
import java.util.*

fun String.toDate(): Date {
    val dateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.CANADA)
    return dateFormat.parse(this) ?: Date()
}

fun Date.toFormattedDate(): CharSequence? {
    val format = SimpleDateFormat("dd/MM/yyyy")
    return format.format(this)
}
