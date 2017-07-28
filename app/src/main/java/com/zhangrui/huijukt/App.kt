package com.zhangrui.huijukt

import android.app.Application
import android.content.Context
import kotlin.properties.Delegates

/**
 * Created by zhangrui on 2017/7/13.
 */
class App : Application() {

    init {
        instance = this
    }
    override fun onCreate() {
        super.onCreate()
    }

    companion object {
        lateinit var instance: App
    }
}