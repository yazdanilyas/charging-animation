package com.example.charginganimations.utils

import android.content.Context
import android.content.SharedPreferences

object PrefUtils {
    private const val PREFS_NAME = "_Prefs_"

    private fun getPreferences(context: Context): SharedPreferences {
        return context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
    }

    fun setBoolean(context: Context, key: String, value: Boolean): Boolean {
        return getPreferences(context).edit().putBoolean(key, value).commit()
    }

    fun getBoolean(context: Context, key: String): Boolean {
        return getPreferences(context).getBoolean(key, false)
    }

    fun getBooleanDefaultTrue(context: Context, key: String): Boolean {
        return getPreferences(context).getBoolean(key, true)
    }

    fun setString(context: Context, key: String, value: String?) {
        if (value != null && value.isNotEmpty())
            getPreferences(context).edit().putString(key, value).apply()
    }

    fun clearStringData(context: Context, key: String) {
        getPreferences(context).edit().putString(key, null).apply()
    }

    fun getString(context: Context, key: String): String? {
        return getPreferences(context).getString(key, null)
    }

    fun setLong(context: Context, key: String, value: Long) {
        getPreferences(context).edit().putLong(key, value).apply()
    }

    fun getLong(context: Context, key: String): Long {
        return getPreferences(context).getLong(key, 0)
    }

    fun setInt(context: Context, key: String, value: Int) {
        getPreferences(context).edit().putInt(key, value).apply()
    }

    fun getInt(context: Context, key: String): Int {
        return getPreferences(context).getInt(key, 0)
    }

    fun setFloat(context: Context, key: String, value: Float) {
        getPreferences(context).edit().putFloat(key, value).apply()
    }

    fun getFloat(context: Context, key: String): Float {
        return getPreferences(context).getFloat(key, 0f)
    }


    fun removeValue(context: Context, key: String) {
        getPreferences(context).edit().remove(key).apply()
    }

    fun clearAllPrefData(pContext: Context) {
        getPreferences(pContext).edit().clear().apply()
    }
}
