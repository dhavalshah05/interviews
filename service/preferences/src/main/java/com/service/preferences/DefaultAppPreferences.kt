package com.service.preferences

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import java.math.BigDecimal

internal class DefaultAppPreferences(context: Context) : AppPreference {

    companion object {
        const val SHARED_PREF_NAME = "app_preference"
    }

    private val sharedPreferences: SharedPreferences =
        context.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE)

    @SuppressLint("CommitPrefEdits")
    override fun putString(name: String, value: String) {
        val editor = sharedPreferences.edit()
        editor!!.putString(name, value)
        editor.apply()
    }

    @SuppressLint("CommitPrefEdits")
    override fun putBoolean(name: String, value: Boolean) {
        val editor = sharedPreferences.edit()
        editor!!.putBoolean(name, value)
        editor.apply()
    }

    override fun getBoolean(name: String): Boolean {
        return sharedPreferences.getBoolean(name, false)
    }

    @Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
    override fun getString(name: String): String {
        return sharedPreferences.getString(name, "") ?: ""
    }

    override fun putInt(name: String, value: Int) {
        val editor = sharedPreferences.edit()
        editor!!.putInt(name, value)
        editor.apply()
    }

    override fun getInt(name: String): Int {
        return sharedPreferences.getInt(name, 0)
    }

    override fun clearAll() {
        sharedPreferences.edit()
            .clear()
            .apply()
    }

    override fun putFloat(name: String, value: Float) {
        val editor = sharedPreferences.edit()
        editor!!.putFloat(name, value)
        editor.apply()
    }

    override fun getFloat(name: String): Float {
        return sharedPreferences.getFloat(name, 0f)
    }

    override fun putDouble(name: String, value: Double) {
        sharedPreferences.edit().putString(name, value.toString()).apply()
    }

    override fun getDouble(name: String): Double {
        return sharedPreferences.getString(name, "0.0")?.toDouble() ?: 0.0
    }

    override fun putBigDecimal(name: String, value: BigDecimal) {
        sharedPreferences.edit().putString(name, value.toString()).apply()
    }

    override fun getBigDecimal(name: String): BigDecimal {
        // return java.lang.Double.longBitsToDouble(sharedPreferences.getLong(name, java.lang.Double.doubleToRawLongBits(0.0)))
        return sharedPreferences.getString(name, "0.0")?.toBigDecimal() ?: BigDecimal.ZERO
    }
}
