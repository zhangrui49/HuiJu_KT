package com.zhangrui.huiju.extensions

import android.annotation.SuppressLint
import kotlin.reflect.KProperty
import android.content.Context
import android.content.SharedPreferences

/**
 * Created by zhangrui on 2017/6/9.
 */
object PreferenceExtensions {
    fun <T> notNullSingleValue() = NotNullValue<T>()
    fun <T> preference(context: Context, name: String,
                       default: T) = Preference(context, name, default)
}

class NotNullValue<T> {
    private var value: T? = null;
    operator fun setValue(thisRef: Any?, property: KProperty<*>, value: T) {
        this.value = if (this.value == null) value
        else
            throw IllegalStateException("${property.name} already initialized")
    }

    operator fun getValue(thisRef: Any?, property: KProperty<*>): T {
        return this.value ?: throw IllegalStateException("${property.name} has not initialized")
    }
}


class Preference<T>(val context: Context, val name: String, val default: T) {
    val prefs: SharedPreferences by lazy { context.getSharedPreferences(name, Context.MODE_PRIVATE) }

    operator fun get(thisRef: Any?, property: KProperty<*>): T {
        return getValue(name, default)
    }

    operator fun set(thisRef: Any?, property: KProperty<*>, value: T):Boolean {
      return  setValue(name, value)
    }

    @Suppress("UNCHECKED_CAST")
    private fun getValue(key: String, default: T): T {
        val res = when (default) {
            is Long -> prefs.getLong(key, default)
            is String -> prefs.getString(name, default)
            is Int -> prefs.getInt(name, default)
            is Boolean -> prefs.getBoolean(name, default)
            is Float -> prefs.getFloat(name, default)
            else -> throw IllegalArgumentException("This type can be saved into Preferences")
        }
        return res as T
    }

    @SuppressLint("CommitPrefEdits")
    private fun setValue(name: String, value: T): Boolean = with(prefs.edit()) {
        when (value) {
            is Long -> putLong(name, value)
            is String -> putString(name, value)
            is Int -> putInt(name, value)
            is Boolean -> putBoolean(name, value)
            is Float -> putFloat(name, value)
            else -> throw IllegalArgumentException("This type can't be saved into Preferences")
        }.commit()
    }

}