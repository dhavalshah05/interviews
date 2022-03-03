package com.template.app.util.date

import android.text.format.DateUtils
import java.text.SimpleDateFormat
import java.util.*

@Suppress("unused")
object DateUtils {

    enum class DateFormat(val value: String) {
        YYYY_MM_DD("yyyy-MM-dd"),
        YYYY_MM_DD_HH_MM_SS("yyyy-MM-dd HH:mm:ss"),
        YYYY_MM_DD_T_HH_MM_SS_SSS_Z("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'"),

        EXPIRY_DATE("MM/yyyy"),
        PRIMARY_DATE("dd MMM, yyyy"),
        PRIMARY_TIME("hh:mm a"),
        SERVER_DATE_TIME("yyyy-MM-dd HH:mm:ss"),
        TRANSACTION_HISTORY_DATE_TIME("dd MMM, yyyy | hh:mm a")
    }

    enum class DateTimeZone(val timeZone: TimeZone) {
        DEFAULT(TimeZone.getDefault()),
        UTC(TimeZone.getTimeZone("UTC"))
    }

    /**
     * Format the provided date string according to DateFormat and DateTimeZone.
     */
    fun format(
            inputDate: String,
            inFormat: DateFormat,
            outFormat: DateFormat,
            inTimeZone: DateTimeZone? = null,
            outTimeZone: DateTimeZone? = null
    ): String {
        val inputDateFormat = SimpleDateFormat(inFormat.value, Locale.getDefault())
        val outputDateFormat = SimpleDateFormat(outFormat.value, Locale.getDefault())

        if (inTimeZone != null) {
            inputDateFormat.timeZone = inTimeZone.timeZone
        }

        if (outTimeZone != null) {
            outputDateFormat.timeZone = outTimeZone.timeZone
        }

        val date = inputDateFormat.parse(inputDate)
        return outputDateFormat.format(date!!)
    }

    /**
     * Format the provided date according to DateFormat and DateTimeZone.
     */
    fun format(
            inputDate: Date,
            outFormat: DateFormat,
            outTimeZone: DateTimeZone? = null
    ): String {
        val outputDateFormat = SimpleDateFormat(outFormat.value, Locale.getDefault())

        if (outTimeZone != null) {
            outputDateFormat.timeZone = outTimeZone.timeZone
        }

        return outputDateFormat.format(inputDate)
    }

    /**
     * Get the current date according to DateFormat and DateTimeZone.
     */
    fun getCurrentDate(
            outFormat: DateFormat,
            outTimeZone: DateTimeZone? = null
    ): String {
        val outputDateFormat = SimpleDateFormat(outFormat.value, Locale.getDefault())
        if (outTimeZone != null) {
            outputDateFormat.timeZone = outTimeZone.timeZone
        }
        val date = Calendar.getInstance()
        return outputDateFormat.format(date.time)
    }

    /**
     * Get the current date according to DateTimeZone.
     */
    fun getCurrentDate(
            outTimeZone: DateTimeZone? = null
    ): Date {
        val date = Calendar.getInstance()
        if (outTimeZone != null) {
            date.timeZone = outTimeZone.timeZone
        }
        return date.time
    }

    /**
     * Parse the date string according to DateFormat and DateTimeZone.
     */
    fun parseDate(
            inputDate: String,
            inFormat: DateFormat,
            inTimeZone: DateTimeZone? = null
    ): Date {
        val inputDateFormat = SimpleDateFormat(inFormat.value, Locale.getDefault())
        if (inTimeZone != null) {
            inputDateFormat.timeZone = inTimeZone.timeZone
        }
        return inputDateFormat.parse(inputDate)!!
    }

    /**
     * Get Date for given year, month and day.
     */
    fun getDate(year: Int, month: Int, day: Int): Date {
        val calendar = Calendar.getInstance()
        calendar.set(Calendar.YEAR, year)
        calendar.set(Calendar.MONTH, month)
        calendar.set(Calendar.DAY_OF_MONTH, day)

        clearCalendarTime(calendar)
        return calendar.time
    }

    /**
     * Get Date for given hour, minute and second.
     */
    fun getTime(hourOfDay: Int, minute: Int, second: Int): Date {
        val calendar = Calendar.getInstance()
        calendar.set(Calendar.HOUR_OF_DAY, hourOfDay)
        calendar.set(Calendar.MINUTE, minute)
        calendar.set(Calendar.SECOND, second)
        return calendar.time
    }

    /**
     *
     */
    fun isToday(
            inputDate: String,
            inFormat: DateFormat,
            inTimeZone: DateTimeZone? = null
    ): Boolean {
        val inputDateFormat = SimpleDateFormat(inFormat.value, Locale.getDefault())
        if (inTimeZone != null) {
            inputDateFormat.timeZone = inTimeZone.timeZone
        }
        val date = inputDateFormat.parse(inputDate)
        return DateUtils.isToday(date.time)
    }

    /**
     *
     */
    fun isToday(
            inputDate: Date
    ): Boolean {
        return DateUtils.isToday(inputDate.time)
    }

    /**
     *
     */
    fun addDays(date: Date, days: Int): Date {
        val calendar = Calendar.getInstance()
        calendar.time = date

        calendar.set(Calendar.DAY_OF_MONTH, calendar.get(Calendar.DAY_OF_MONTH) + days)
        return calendar.time
    }

    /**
     * Clear time for given Calendar instance.
     */
    private fun clearCalendarTime(calendar: Calendar) {
        calendar.set(Calendar.HOUR_OF_DAY, 0)
        calendar.set(Calendar.MINUTE, 0)
        calendar.set(Calendar.SECOND, 0)
        calendar.set(Calendar.MILLISECOND, 0)
    }
}