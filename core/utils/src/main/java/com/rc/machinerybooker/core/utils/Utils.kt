package com.rc.machinerybooker.core.utils

import java.time.Instant
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.ZoneId
import java.util.*

fun LocalDate.toLong() = this.atStartOfDay(ZoneId.systemDefault()).toEpochSecond() * 1000;
fun LocalDateTime.toLong() = this.atZone(ZoneId.systemDefault()).toEpochSecond() * 1000;
fun Long.toLocalDateTime(): LocalDateTime = if (this == 0L) {
    LocalDateTime.ofInstant(
        Instant.ofEpochSecond(0), TimeZone.getTimeZone("UTC").toZoneId()
    )
} else {
    LocalDateTime.ofInstant(
        Instant.ofEpochSecond(this / 1000), TimeZone.getDefault().toZoneId()
    )
}

fun Long.toLocalDateTimeUTC(): LocalDateTime = LocalDateTime.ofInstant(
    Instant.ofEpochSecond(this / 1000), TimeZone.getTimeZone("UTC").toZoneId()
);
