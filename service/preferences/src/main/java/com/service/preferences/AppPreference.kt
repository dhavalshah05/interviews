package com.service.preferences

import java.math.BigDecimal

interface AppPreference {
    fun putString(name: String, value: String)
    fun getString(name: String): String

    fun putBoolean(name: String, value: Boolean)
    fun getBoolean(name: String): Boolean

    fun putInt(name: String, value: Int)
    fun getInt(name: String): Int

    fun clearAll()

    fun putFloat(name: String, value: Float)
    fun getFloat(name: String): Float

    fun putDouble(name: String, value: Double)
    fun getDouble(name: String): Double

    fun putBigDecimal(name: String, value: BigDecimal)
    fun getBigDecimal(name: String): BigDecimal
}
