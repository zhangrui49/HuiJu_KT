package com.zhangrui.huijukt.fragment


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.content.ContextCompat
import android.support.v4.view.ViewPager
import android.view.ViewGroup
import com.zhangrui.huijukt.R
import com.zhangrui.huijukt.activity.ImageActivity
import com.zhangrui.huijukt.activity.WelfareActivity
import com.zhangrui.huijukt.adapter.GankPagerAdapter
import com.zhangrui.huijukt.base.BaseFragment
import com.zhangrui.huijukt.mvp.contract.GankContract
import com.zhangrui.huijukt.mvp.presenter.GankPresenter
import kotlinx.android.synthetic.main.activity_web.view.*
import kotlinx.android.synthetic.main.fabmenu_gank.*
import kotlinx.android.synthetic.main.fragment_gank.*
import org.jetbrains.anko.support.v4.ctx
import org.jetbrains.anko.support.v4.startActivity


class GankFragment : BaseFragment<GankPresenter>(), GankContract.View {
    companion object {
        fun newInstance(): GankFragment {
            return GankFragment()
        }
    }

    private val TITLES = arrayOf("Android", "iOS", "前端", "休息视频", "拓展资源")
    private var gankPagerAdapter: GankPagerAdapter? = null;
    override fun generateLayoutId(): Int {
        return R.layout.fragment_gank
    }

    override fun initView() {
        var list = ArrayList<Fragment>()
        for (i in 0..TITLES.size - 1) {
            val bundle = Bundle()
            bundle.putString("type", TITLES[i])
            val tabFragment = GankTabFragment()
            tabFragment.arguments = bundle
            list.add(tabFragment)
        }
        toolbar.title = "干货"
        val mImageArray = intArrayOf(R.drawable.android, R.drawable.ios, R.drawable.web, R.drawable.rest, R.drawable.out)
        var mColorArray = intArrayOf(android.R.color.holo_blue_light, android.R.color.holo_red_light, android.R.color.holo_orange_light, android.R.color.holo_green_light, R.color.colorPrimary)
        gankPagerAdapter = GankPagerAdapter(fragmentManager, list, TITLES)
        pager.offscreenPageLimit = 1
        pager.adapter = gankPagerAdapter
        pager_tabs.setupWithViewPager(pager)
        pager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(p0: Int) {
            }

            override fun onPageScrolled(p0: Int, p1: Float, p2: Int) {
            }

            override fun onPageSelected(p0: Int) {
                image_type.setImageResource(mImageArray[p0])
                collapsingToolbarLayout.setContentScrimColor(
                        ContextCompat.getColor(
                                ctx, mColorArray[p0]));
            }
        })
        action_date.title = "往期回顾"
        action_rest.title = "福利时间"
        action_rest.setOnClickListener {
            startActivity<WelfareActivity>()
            menu.collapse()
        }
        action_date.setOnClickListener {
            menu.collapse()
        }
    }

    override fun generatePresenter(): GankPresenter {
        return GankPresenter(ctx, this)
    }


}
