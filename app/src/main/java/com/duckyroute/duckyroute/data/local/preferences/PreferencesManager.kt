package com.duckyroute.duckyroute.data.local.preferences

import android.content.Context
import android.content.SharedPreferences

class PreferencesManager (context: Context) {
    private val sharedPreferences: SharedPreferences = context.getSharedPreferences("duckyroute_preferences", Context.MODE_PRIVATE)

    fun saveString(key: String, value: String) {
        val editor = sharedPreferences.edit()
        editor.putString(key, value)
        editor.apply()
    }

    fun getString(key: String, defaultValue: String): String? {
        return sharedPreferences.getString(key, defaultValue)
    }

    fun deleteString(key: String) {
        val editor = sharedPreferences.edit()
        editor.remove(key)
        editor.apply()
    }

    fun contains(key: String): Boolean {
        return sharedPreferences.contains(key)
    }
}