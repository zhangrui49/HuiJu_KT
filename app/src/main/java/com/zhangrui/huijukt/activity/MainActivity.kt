package com.zhangrui.huijukt.activity

import android.support.design.widget.NavigationView
import android.support.v4.app.Fragment
import android.support.v4.view.GravityCompat
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.ActionBarDrawerToggle
import android.view.MenuItem
import com.zhangrui.huijukt.R
import com.zhangrui.huijukt.base.BaseActivity
import com.zhangrui.huijukt.fragment.GankFragment
import com.zhangrui.huijukt.fragment.MovieFragment
import com.zhangrui.huijukt.fragment.VideoFragment
import com.zhangrui.huijukt.fragment.ZhihuFragment
import com.zhangrui.huijukt.mvp.contract.HomeContract
import com.zhangrui.huijukt.mvp.presenter.HomePresenter
import kotlinx.android.synthetic.main.activity_home.*


class MainActivity : BaseActivity<HomePresenter>(), HomeContract.View, NavigationView.OnNavigationItemSelectedListener {
    var mFragment: Fragment? = null

    override fun initView() {
//        setSupportActionBar(toolbar)
        val toggle = ActionBarDrawerToggle(
                this, drawer_layout, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer_layout.addDrawerListener(toggle);
        nav_view.setNavigationItemSelectedListener(this)
        toggle.syncState();
        mFragment = GankFragment()
        replaceFragment()
    }

    override fun generateLayoutId(): Int {
        return R.layout.activity_home
    }

    override fun generatePresenter(): HomePresenter {
        return HomePresenter(this, this)
    }

    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START);
        } else {
            // super.onBackPressed();
            moveTaskToBack(false)
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        val id = item.itemId

        if (id == R.id.gank) {
            mFragment = GankFragment()
            replaceFragment()
        } else if (id == R.id.zhihu) {
            mFragment = ZhihuFragment()
            replaceFragment()
        } else if (id == R.id.meipai) {
            mFragment = VideoFragment()
            replaceFragment()
        } else if (id == R.id.douban) {
            mFragment = MovieFragment()
            replaceFragment()
        } else if (id == R.id.nav_share) {

            //  showShare("汇聚", "我发现了一款不错的App,邀请你们下载", "", "")

        } else if (id == R.id.nav_send) {

        }

        val drawer = findViewById(R.id.drawer_layout) as DrawerLayout
        drawer.closeDrawer(GravityCompat.START)
        return true
    }


    fun replaceFragment() {
        val ft = supportFragmentManager.beginTransaction()
        if (mFragment != null && mFragment!!.isAdded) {
            ft.show(mFragment)
        } else {
            ft.replace(R.id.main_content, mFragment)
        }
        ft.commitAllowingStateLoss()
    }

}
