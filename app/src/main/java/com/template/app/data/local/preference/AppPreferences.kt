package com.template.app.data.local.preference

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import java.math.BigDecimal

class AppPreferences(context: Context) {

    companion object {
        const val SHARED_PREF_NAME = "app_preference"
    }

    private val sharedPreferences: SharedPreferences =
        context.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE)


    @SuppressLint("CommitPrefEdits")
    fun putString(name: String, value: String) {
        val editor = sharedPreferences.edit()
        editor!!.putString(name, value)
        editor.apply()
    }


    @SuppressLint("CommitPrefEdits")
    fun putBoolean(name: String, value: Boolean) {
        val editor = sharedPreferences.edit()
        editor!!.putBoolean(name, value)
        editor.apply()
    }

    fun getBoolean(name: String): Boolean {
        return sharedPreferences.getBoolean(name, false)
    }

    @Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
    fun getString(name: String): String {
        return sharedPreferences.getString(name, "") ?: ""
    }

    fun getInt(name: String): Int {
        return sharedPreferences.getInt(name, 0)
    }


    fun clearAll() {
        sharedPreferences.edit()
            .clear()
            .apply()
    }

    fun putFloat(name: String, value: Float) {
        val editor = sharedPreferences.edit()
        editor!!.putFloat(name, value)
        editor.apply()
    }

    fun getFloat(name: String): Float {
        return sharedPreferences.getFloat(name, 0f)
    }

    fun putDouble(name: String, value: Double) {
        sharedPreferences.edit().putString(name, value.toString()).apply()
    }

    fun getDouble(name: String): Double {
        return sharedPreferences.getString(name, "0.0")?.toDouble() ?: 0.0
    }

    fun putBigDecimal(name: String, value: BigDecimal) {
        sharedPreferences.edit().putString(name, value.toString()).apply()
    }

    fun getBigDecimal(name: String): BigDecimal {
        //return java.lang.Double.longBitsToDouble(sharedPreferences.getLong(name, java.lang.Double.doubleToRawLongBits(0.0)))
        return sharedPreferences.getString(name, "0.0")?.toBigDecimal() ?: BigDecimal.ZERO
    }
}
