package com.zhangrui.huiju.widget

import android.content.Context
import android.support.v4.view.ViewPager
import android.util.AttributeSet
import android.view.MotionEvent



/**
 * Created by zhangrui on 2017/7/19.
 */
open class FixMultiViewPager(context: Context) : ViewPager(context) {
    constructor(context: Context, attributeSet: AttributeSet) : this(context) {
    }
    override fun onInterceptTouchEvent(ev: MotionEvent): Boolean {
        try {
            return super.onInterceptTouchEvent(ev)
        } catch (ex: IllegalArgumentException) {
            ex.printStackTrace()
        }
        return false
    }
}