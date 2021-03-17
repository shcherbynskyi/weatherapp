package com.example.weather_app.core.extensions

import android.text.format.DateUtils
import java.util.*

fun Long.getTime(): String {
    val calendar = Calendar.getInstance()
    calendar.timeInMillis = this * 1000L
    val hour = calendar.get(Calendar.HOUR_OF_DAY)
    return "$hour:00"
}

fun Long.getDate(): String {
    val calendar = Calendar.getInstance()
    calendar.timeInMillis = this * 1000L
    val day = calendar.get(Calendar.DAY_OF_MONTH)
    val month = calendar.get(Calendar.MONTH)
    return if (DateUtils.isToday(this)) "Today" else "$day/${month + 1}"
}