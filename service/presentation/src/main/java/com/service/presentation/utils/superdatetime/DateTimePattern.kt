package com.service.presentation.utils.superdatetime

interface DateTimePattern {
    fun getPattern(): String

    companion object {
        val PRIMARY = object : DateTimePattern {
            override fun getPattern(): String {
                return "dd MMM, yyyy"
            }
        }
    }
}