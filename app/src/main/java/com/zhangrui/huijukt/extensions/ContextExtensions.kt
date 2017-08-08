package com.zhangrui.huijukt.extensions

import android.app.Activity
import android.app.Dialog
import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.Toast
import com.kcode.gankotlin.ui.fragment.ProgressFragment
import com.zhangrui.huijukt.R

/**
 *
 * Created by zhangrui on 2017/7/11.
 */

/**
 *  anko 中包含此方法
 */
fun Context.toast(msg: String, length: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(this, msg, length).show()
}

fun Activity.warn(msg: String) {
    val dialog = Dialog(this)
    dialog.setContentView(R.layout.dialog_warn)
    dialog.show()
}

fun AppCompatActivity.showProgress(msg: String = "加载中...") {
    dismissProgress()
    ProgressFragment.newInstance(msg).show(supportFragmentManager, ProgressFragment::class.java.simpleName)
}

fun AppCompatActivity.dismissProgress() {
    (supportFragmentManager.findFragmentByTag(ProgressFragment::class.java.simpleName) as ProgressFragment?)?.dismiss()
}

inline fun <reified T> T.debug(log: Any) {
    Log.d(T::class.simpleName, log.toString())
}