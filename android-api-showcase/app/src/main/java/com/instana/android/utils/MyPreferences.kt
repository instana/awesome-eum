package com.instana.android.utils

import android.content.Context
import android.content.SharedPreferences

/**
 * Helper class for managing shared preferences.
 * TODO: Make it encrypted shared preference and injectable.
 */
class MyPreferences(context: Context) {
    private val sharedPreferences: SharedPreferences

    init {
        sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
    }

    private val defaultPref: SharedPreferences = sharedPreferences

    /**
     * Puts a value for the given [key] in the shared preferences.
     * @param key The key for the preference.
     * @param value The value to be stored.
     */
    fun setValue(key: String, value: Any?) = when (value) {
        is String? -> edit { it.putString(key, value) }
        is Int -> edit { it.putInt(key, value) }
        is Boolean -> edit { it.putBoolean(key, value) }
        is Float -> edit { it.putFloat(key, value) }
        is Long -> edit { it.putLong(key, value) }
        else -> throw UnsupportedOperationException("Not yet implemented")
    }

    /**
     * Retrieves the value associated with the given [key] from the shared preferences.
     * @param key The key for the preference.
     * @param defaultValue The optional default value.
     * @return The value associated with the key, or the defaultValue if not found.
     */
    fun getValue(key: String, defaultValue: Any?): Any? {
        return when (defaultValue) {
            is String -> defaultPref.getString(key, defaultValue as? String)
            is Int -> defaultPref.getInt(key, defaultValue as? Int ?: -1)
            is Boolean -> defaultPref.getBoolean(key, defaultValue as? Boolean ?: false)
            is Float -> defaultPref.getFloat(key, defaultValue as? Float ?: -1f)
            is Long -> defaultPref.getLong(key, defaultValue as? Long ?: -1)
            else -> throw UnsupportedOperationException("Not yet implemented")
        }
    }

    /**
     * Performs the shared preferences edit operation.
     * @param operation The edit operation to be performed.
     */
    private fun edit(operation: (SharedPreferences.Editor) -> Unit) {
        val editor = defaultPref.edit()
        operation(editor)
        editor.commit()
    }

    companion object {
        private const val PREF_NAME = "InstanaPreferences"
    }
}
