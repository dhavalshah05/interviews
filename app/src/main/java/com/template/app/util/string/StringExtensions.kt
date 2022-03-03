package com.template.app.util.string

fun String.formatDecimal(): String {
    val text = this
    val values = text.split(".")
    if (values.size == 1) {
        return values.first()
    } else {
        val lastValue = values.last()
        val lastValueInt = lastValue.toInt()
        if (lastValueInt > 0) {
            if (lastValue.length > 2) {
                val newLastValue = lastValue.substring(0, 2)
                return "${values.first()}.$newLastValue"
            } else {
                return "${values.first()}.${values.last()}"
            }
        } else {
            return values.first()
        }
    }
}