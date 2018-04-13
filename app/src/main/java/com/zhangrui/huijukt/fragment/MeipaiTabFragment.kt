package com.zhangrui.huijukt.fragment

import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import com.lcodecore.tkrefreshlayout.RefreshListenerAdapter
import com.lcodecore.tkrefreshlayout.TwinklingRefreshLayout
import com.lcodecore.tkrefreshlayout.header.progresslayout.ProgressLayout
import com.zhangrui.huijukt.R
import com.zhangrui.huijukt.activity.WebActivity
import com.zhangrui.huijukt.adapter.MeipaiTabAdapter
import com.zhangrui.huijukt.base.BaseFragment
import com.zhangrui.huijukt.bean.meipai.Video
import com.zhangrui.huijukt.extensions.dip2px
import com.zhangrui.huijukt.mvp.contract.MeipaiTabContract
import com.zhangrui.huijukt.mvp.presenter.MeipaiTabPresenter
import com.zhy.adapter.recyclerview.MultiItemTypeAdapter
import kotlinx.android.synthetic.main.common_data_layout.*
import org.jetbrains.anko.support.v4.ctx
import org.jetbrains.anko.support.v4.startActivity

/**
 *
 * Created by zhangrui on 2017/7/27.
 */
class MeipaiTabFragment : BaseFragment<MeipaiTabPresenter>(), MeipaiTabContract.View {

    var movieAdapter: MeipaiTabAdapter? = null
    var page = 1
    val PAGE_SIZE = 10
    var list: ArrayList<Video>? = null
    override fun showLoading() {
    }

    override fun showError(msg: String) {
    }

    override fun showComplete() {
        tkRefreshLayout.finishRefreshing()
        tkRefreshLayout.finishLoadmore()
    }

    override fun showData(data: List<Video>) {
        data.let { list!!.addAll(it) }
        movieAdapter?.notifyDataSetChanged()
    }

    override fun initView() {
        list = ArrayList<Video>()
        movieAdapter = MeipaiTabAdapter(ctx, R.layout.item_video_tab, list!!)
        recyclerview.adapter = movieAdapter
        recyclerview.layoutManager = LinearLayoutManager(ctx, LinearLayoutManager.VERTICAL, false)
        tkRefreshLayout.setHeaderView(ProgressLayout(ctx))
        tkRefreshLayout.setBottomHeight(180f.dip2px(ctx))
        tkRefreshLayout.setOnRefreshListener(object : RefreshListenerAdapter() {
            override fun onRefresh(refreshLayout: TwinklingRefreshLayout?) {
                page =1
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
                startActivity<WebActivity>("url" to list?.get(position)?.url.toString())
            }

            override fun onItemLongClick(view: View?, holder: RecyclerView.ViewHolder?, position: Int): Boolean {
                return false
            }
        })
    }

    override fun generateLayoutId(): Int {
        return R.layout.common_data_layout
    }

    override fun generatePresenter(): MeipaiTabPresenter {
        return MeipaiTabPresenter(this)
    }

    fun getMovieData() {
        val id = arguments!!.getString("id")
        val map = HashMap<String, Any>()
        map.put("id", id)
        map.put("page", page)
        map.put("count", PAGE_SIZE)
        mPresenter?.requestData(map)
    }

}