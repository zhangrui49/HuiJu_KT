package com.zhangrui.huijukt.fragment

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.lcodecore.tkrefreshlayout.RefreshListenerAdapter
import com.lcodecore.tkrefreshlayout.TwinklingRefreshLayout
import com.lcodecore.tkrefreshlayout.header.progresslayout.ProgressLayout
import com.zhangrui.huijukt.R
import com.zhangrui.huijukt.activity.AnimationImageActivity
import com.zhangrui.huijukt.activity.MovieDetailActivity
import com.zhangrui.huijukt.adapter.MovieTabAdapter
import com.zhangrui.huijukt.base.BaseFragment
import com.zhangrui.huijukt.bean.douban.Movie
import com.zhangrui.huijukt.bean.douban.MovieDetail
import com.zhangrui.huijukt.extensions.dip2px
import com.zhangrui.huijukt.mvp.contract.MovieTabContract
import com.zhangrui.huijukt.mvp.presenter.MovieTabPresenter
import com.zhy.adapter.recyclerview.MultiItemTypeAdapter
import kotlinx.android.synthetic.main.common_data_layout.*
import org.jetbrains.anko.support.v4.ctx

/**
 *
 * Created by zhangrui on 2017/7/27.
 */
class MovieTabFragment : BaseFragment<MovieTabPresenter>(), MovieTabContract.View {

    var movieAdapter: MovieTabAdapter? = null
    var page = 0
    val PAGE_SIZE = 10
    var list: ArrayList<MovieDetail>? = null
    override fun showLoading() {
    }

    override fun showError(msg: String) {
    }

    override fun showComplete() {
        tkRefreshLayout.finishRefreshing()
        tkRefreshLayout.finishLoadmore()
    }

    override fun showData(data: Movie) {
        data.subjects?.let { list!!.addAll(it) }
        movieAdapter?.notifyDataSetChanged()
    }

    override fun initView() {
        list = ArrayList<MovieDetail>()
        movieAdapter = MovieTabAdapter(ctx, R.layout.item_movie, list!!)
        recyclerview.adapter = movieAdapter;
        recyclerview.layoutManager = LinearLayoutManager(ctx, LinearLayoutManager.VERTICAL, false)
        tkRefreshLayout.setHeaderView(ProgressLayout(ctx))
        tkRefreshLayout.setBottomHeight(180f.dip2px(ctx))
        tkRefreshLayout.setOnRefreshListener(object : RefreshListenerAdapter() {
            override fun onRefresh(refreshLayout: TwinklingRefreshLayout?) {
                page = 0
                list?.clear()
                getMovieData()
            }

            override fun onLoadMore(refreshLayout: TwinklingRefreshLayout?) {
                page++

                getMovieData()
            }
        })
        tkRefreshLayout.startRefresh()
        movieAdapter?.setOnItemClickListener(object : MultiItemTypeAdapter.OnItemClickListener {

            override fun onItemClick(view: View?, holder: RecyclerView.ViewHolder?, position: Int) {
                MovieDetailActivity.start(activity, view!!, list!![position])
            }

            override fun onItemLongClick(view: View?, holder: RecyclerView.ViewHolder?, position: Int): Boolean {
                return false
            }
        })
    }

    override fun generateLayoutId(): Int {
        return R.layout.common_data_layout
    }

    override fun generatePresenter(): MovieTabPresenter {
        return MovieTabPresenter(ctx, this)
    }

    fun getMovieData() {
        val type = arguments!!.getString("type")
        val map = HashMap<String, Any>()
        map.put("count", PAGE_SIZE)
        map.put("start", page * PAGE_SIZE + 1)
        mPresenter?.requestData(type, map)
    }

}