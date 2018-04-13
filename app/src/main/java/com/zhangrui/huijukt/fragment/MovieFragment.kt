package com.zhangrui.huijukt.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.view.ViewPager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.zhangrui.huijukt.R
import com.zhangrui.huijukt.adapter.MoviePagerAdapter
import com.zhangrui.huijukt.base.BaseFragment
import com.zhangrui.huijukt.mvp.contract.MovieContract
import com.zhangrui.huijukt.mvp.presenter.MoviePresenter
import kotlinx.android.synthetic.main.fragment_movie.*
import kotlinx.android.synthetic.main.title.*
import org.jetbrains.anko.support.v4.ctx


/**
 *
 * Created by zhangrui on 2017/7/20.
 */
class MovieFragment : BaseFragment<MoviePresenter>(), MovieContract.View {
    var movieAdapter: MoviePagerAdapter? = null
    private val TYPES = arrayOf("coming_soon", "in_theaters", "top250")
    override fun initView() {
        val list = ArrayList<Fragment>()
        for (i in 0 until TYPES.size) {
            val bundle = Bundle()
            bundle.putString("type", TYPES[i])
            val tabFragment = MovieTabFragment()
            tabFragment.arguments = bundle
            list.add(tabFragment)
        }
        movieAdapter = MoviePagerAdapter(childFragmentManager, list)
        pager.offscreenPageLimit = 1
        pager.adapter = movieAdapter
        bottom.setOnNavigationItemReselectedListener {

        }
        bottom.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.in_theaters -> {
                    pager.setCurrentItem(1, true)
                }
                R.id.coming_soon -> {
                    pager.setCurrentItem(0, true)
                }
                R.id.top250 -> {
                    pager.setCurrentItem(2, true)
                }
//                R.id.weekly -> {
//                    pager.setCurrentItem(3, true)
//                }
//                R.id.new_movies -> {
//                    pager.setCurrentItem(4, true)
//                }
            }
            false
        }
        pager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(state: Int) {
            }

            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
            }

            override fun onPageSelected(position: Int) {
                bottom.menu.getItem(position).isChecked = true
                toolbar.title = bottom.menu.getItem(position).title
            }

        })
        pager.setCurrentItem(1, true)
    }

    override fun generateLayoutId(): Int {
        return R.layout.fragment_movie
    }

    override fun generatePresenter(): MoviePresenter {
        return MoviePresenter(this)
    }
}