package com.myapplication.bootcamp.carapp.utils.common

import android.util.Log
import org.threeten.bp.LocalDateTime
import org.threeten.bp.format.DateTimeFormatter
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit

object TimeUtils {

    private const val SECOND_MILLIS = 1000
    private const val MINUTE_MILLIS = 60 * SECOND_MILLIS
    private const val HOUR_MILLIS = 60 * MINUTE_MILLIS
    private const val DAY_MILLIS = 24 * HOUR_MILLIS
    val df: DateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")

    fun getTimeAgo(date: Date): String {
        val time = date.time
        val now = System.currentTimeMillis()
        if (time > now || time <= 0) return ""

        val diff = now - time
        return if (diff < MINUTE_MILLIS) {
            "just now"
        } else if (diff < 2 * MINUTE_MILLIS) {
            "a minute ago"
        } else if (diff < 50 * MINUTE_MILLIS) {
            "${diff / MINUTE_MILLIS} minutes ago"
        } else if (diff < 90 * MINUTE_MILLIS) {
            "an hour ago"
        } else if (diff < 24 * HOUR_MILLIS) {
            "${diff / HOUR_MILLIS} hours ago"
        } else if (diff < 48 * HOUR_MILLIS) {
            "yesterday"
        } else {
            "${diff / DAY_MILLIS} days ago"
        }
    }

    fun getDateDifference(profileCreationDate:String):String{
        val creationDate: Date = SimpleDateFormat("yyyy-MM-dd").parse(profileCreationDate.substring(0,10))
        val currentDate: Date = SimpleDateFormat("yyyy-MM-dd").parse(LocalDateTime.now().toString().substring(0,10))

        val diff=currentDate.time-creationDate.time

        return TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS).toString()
    }
}