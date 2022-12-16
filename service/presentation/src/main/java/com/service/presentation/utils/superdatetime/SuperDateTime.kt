package com.service.presentation.utils.superdatetime

import java.time.*
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoUnit
import java.util.Date

object SuperDateTime {

    /**
     * Retrieve current date time for given [timeZone].
     */
    fun now(timeZone: TimeZone = TimeZone.DEFAULT): ZonedDateTime {
        return ZonedDateTime.now(timeZone.getZoneId())
    }

    /**
     * Convert datetime text to [ZonedDateTime].
     */
    fun toDateTime(
        inputText: String,
        inputPattern: DateTimePattern,
        inputTimeZone: TimeZone = TimeZone.DEFAULT
    ): ZonedDateTime {
        val formatter = DateTimeFormatter.ofPattern(inputPattern.getPattern())
        val localDateTime = LocalDateTime.parse(inputText, formatter)
        return localDateTime.atZone(inputTimeZone.getZoneId())
    }

    /**
     * Convert [date] to [ZonedDateTime]
     */
    fun toDateTime(
        date: Date,
        inputTimeZone: TimeZone = TimeZone.DEFAULT
    ): ZonedDateTime {
        return date.toInstant().atZone(inputTimeZone.getZoneId())
    }

    /**
     * Convert [dateTime] to [Date]
     */
    fun toDate(
        dateTime: ZonedDateTime,
    ): Date {
        return Date.from(dateTime.toInstant())
    }

    /**
     * Convert [year], [month] and [day] to [ZonedDateTime].
     */
    fun toDateTime(
        year: Int,
        month: Int,
        day: Int,
        inputTimeZone: TimeZone = TimeZone.DEFAULT
    ): ZonedDateTime {
        val localDateTime = LocalDate.of(year, month, day).atStartOfDay()
        return localDateTime.atZone(inputTimeZone.getZoneId())
    }

    /**
     * Convert [hour], [minute], [second] and [nanoSecond] to [ZonedDateTime].
     */
    fun toDateTime(
        hour: Int,
        minute: Int,
        second: Int,
        nanoSecond: Int = 0,
        inputTimeZone: TimeZone = TimeZone.DEFAULT
    ): ZonedDateTime {
        val today = LocalDate.now()
        val localDateTime = LocalTime.of(hour, minute, second, nanoSecond).atDate(today)
        return localDateTime.atZone(inputTimeZone.getZoneId())
    }

    /**
     * Convert [year], [month], [day], [hour], [minute], [second] and [nanoSecond] to [ZonedDateTime].
     */
    fun toDateTime(
        year: Int,
        month: Int,
        day: Int,
        hour: Int,
        minute: Int,
        second: Int,
        nanoSecond: Int = 0,
        inputTimeZone: TimeZone = TimeZone.DEFAULT
    ): ZonedDateTime {
        val localDateTime = LocalDateTime.of(year, month, day, hour, minute, second, nanoSecond)
        return localDateTime.atZone(inputTimeZone.getZoneId())
    }

    /**
     * Convert [timestamp] to [ZonedDateTime].
     */
    fun toDateTime(
        timestamp: Long,
        inputTimeZone: TimeZone = TimeZone.DEFAULT
    ): ZonedDateTime {
        val instant = Instant.ofEpochMilli(timestamp)
        return ZonedDateTime.ofInstant(instant, inputTimeZone.getZoneId())
    }

    /**
     * Convert [ZonedDateTime] to timestamp.
     */
    fun toTimeStamp(dateTime: ZonedDateTime): Long {
        return Instant.from(dateTime).toEpochMilli()
    }

    /**
     * Convert [ZonedDateTime] to given [DateTimePattern]
     */
    fun toText(
        dateTime: ZonedDateTime,
        outputPattern: DateTimePattern
    ): String {
        val formatter = DateTimeFormatter.ofPattern(outputPattern.getPattern())
        return dateTime.format(formatter)
    }

    /**
     * Convert [ZonedDateTime] to given [TimeZone].
     */
    fun convertTimeZone(
        dateTime: ZonedDateTime,
        outputTimeZone: TimeZone
    ): ZonedDateTime {
        return dateTime.withZoneSameInstant(outputTimeZone.getZoneId())
    }

    /**
     * Calculate days between [startDateTime] and [endDateTime].
     */
    fun getDaysBetween(
        startDateTime: ZonedDateTime,
        endDateTime: ZonedDateTime
    ): Long {
        return ChronoUnit.DAYS.between(startDateTime, endDateTime)
    }

    fun isToday(
        dateTime: ZonedDateTime
    ): Boolean {
        val today = ZonedDateTime.now(dateTime.zone)
        return dateTime.toLocalDate().isEqual(today.toLocalDate())
    }

    fun isDateInPast(
        dateTime: ZonedDateTime
    ): Boolean {
        val today = ZonedDateTime.now(dateTime.zone)
        return dateTime.toLocalDate().isBefore(today.toLocalDate())
    }

    fun isDateTimeInPast(
        dateTime: ZonedDateTime
    ): Boolean {
        val today = ZonedDateTime.now(dateTime.zone)
        return dateTime.isBefore(today)
    }

    fun isDateInFuture(
        dateTime: ZonedDateTime
    ): Boolean {
        val today = ZonedDateTime.now(dateTime.zone)
        return dateTime.toLocalDate().isAfter(today.toLocalDate())
    }

    fun isDateTimeInFuture(
        dateTime: ZonedDateTime
    ): Boolean {
        val today = ZonedDateTime.now(dateTime.zone)
        return dateTime.isAfter(today)
    }

    fun add(
        inputDateTime: ZonedDateTime,
        days: Long = 0L,
        months: Long = 0L,
        years: Long = 0L,
        weeks: Long = 0L,
        hours: Long = 0L,
        minutes: Long = 0L,
        seconds: Long = 0L
    ): ZonedDateTime {
        var result = inputDateTime

        if (years != 0L)
            result = result.plusYears(days)

        if (months != 0L)
            result = result.plusMonths(months)

        if (weeks != 0L)
            result = result.plusWeeks(months)

        if (days != 0L)
            result = result.plusDays(days)

        if (hours != 0L)
            result = result.plusHours(hours)

        if (minutes != 0L)
            result = result.plusMinutes(minutes)

        if (seconds != 0L)
            result = result.plusSeconds(seconds)

        return result
    }

    fun subtract(
        inputDateTime: ZonedDateTime,
        days: Long = 0L,
        months: Long = 0L,
        years: Long = 0L,
        weeks: Long = 0L,
        hours: Long = 0L,
        minutes: Long = 0L,
        seconds: Long = 0L
    ): ZonedDateTime {
        var result = inputDateTime

        if (years != 0L)
            result = result.minusYears(days)

        if (months != 0L)
            result = result.minusMonths(months)

        if (weeks != 0L)
            result = result.minusWeeks(months)

        if (days != 0L)
            result = result.minusDays(days)

        if (hours != 0L)
            result = result.minusHours(hours)

        if (minutes != 0L)
            result = result.minusMinutes(minutes)

        if (seconds != 0L)
            result = result.minusSeconds(seconds)

        return result
    }

    fun calculateDurationBetween(
        startDateTime: ZonedDateTime,
        endDateTime: ZonedDateTime,
        onCalculated: (days: Long, hours: Long, minutes: Long, seconds: Long) -> Unit
    ) {
        val duration = Duration.between(startDateTime, endDateTime)
        val days = duration.toDays()
        val hours = duration.toHours() % 24
        val minutes = duration.toMinutes() % 60
        val seconds = duration.toSeconds() % 60
        onCalculated.invoke(days, hours, minutes, seconds)
    }
}