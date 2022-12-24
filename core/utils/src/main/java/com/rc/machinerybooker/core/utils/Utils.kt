package com.rc.machinerybooker.core.utils

import java.time.Instant
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.ZoneId
import java.util.*

fun LocalDate.toLong() = this.atStartOfDay(ZoneId.systemDefault()).toEpochSecond()*1000;
fun LocalDateTime.toLong() = this.atZone(ZoneId.systemDefault()).toEpochSecond()*1000;
fun Long.toLocalDateTime(): LocalDateTime = LocalDateTime.ofInstant(
    Instant.ofEpochSecond(this/1000), TimeZone.getDefault().toZoneId());
