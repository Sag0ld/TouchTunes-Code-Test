package com.example.ui

import java.text.SimpleDateFormat
import java.util.*

fun Date.toFormattedDate(): CharSequence? {
    val format = SimpleDateFormat("dd/MM/yyyy", Locale.CANADA)
    return format.format(this)
}
