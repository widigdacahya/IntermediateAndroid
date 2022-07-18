package com.dicoding.newsapp.util

import java.time.Instant
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.*

object DateFormatter {

    fun formatDate(currentDateString: String, targetTimeZone:String): String {

        val isntant = Instant.parse(currentDateString)
        val formatter = DateTimeFormatter.ofPattern("dd MMM yyy | HH:mm")
            .withZone(ZoneId.of(targetTimeZone))

        return formatter.format(isntant)
    }

}