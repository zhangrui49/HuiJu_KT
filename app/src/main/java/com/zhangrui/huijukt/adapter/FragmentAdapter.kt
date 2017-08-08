package com.zhangrui.huijukt.adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter

import java.util.ArrayList

/**
 * viewpager adapter
 */

class FragmentAdapter(fm: FragmentManager, fragmentList: List<Fragment>?, private val mTitles: Array<String>) : FragmentPagerAdapter(fm) {

    private val fragmentList: List<Fragment>?

    init {
        var fragmentList = fragmentList
        if (fragmentList == null) {
            fragmentList = ArrayList<Fragment>()
        }
        this.fragmentList = fragmentList
    }

    override fun getItem(position: Int): Fragment? {
        return if (isEmpty) null else fragmentList!![position]
    }

    override fun getCount(): Int {
        return if (isEmpty) 0 else fragmentList!!.size
    }

    val isEmpty: Boolean
        get() = fragmentList == null

    override fun getPageTitle(position: Int): CharSequence {
        return mTitles[position]
    }

}
