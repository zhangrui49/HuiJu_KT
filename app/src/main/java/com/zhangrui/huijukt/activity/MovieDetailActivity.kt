package com.zhangrui.huijukt.activity

import android.app.Activity
import android.content.Intent
import android.support.v4.app.ActivityCompat
import android.support.v4.app.ActivityOptionsCompat
import android.support.v4.util.Pair
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import com.bumptech.glide.Glide
import com.zhangrui.huijukt.R
import com.zhangrui.huijukt.adapter.CastAdapter
import com.zhangrui.huijukt.base.BaseActivity
import com.zhangrui.huijukt.bean.douban.Casts
import com.zhangrui.huijukt.bean.douban.MovieDetail
import com.zhangrui.huijukt.mvp.contract.MovieDetailContract
import com.zhangrui.huijukt.mvp.presenter.MovieDetailPresenter
import com.zhy.adapter.recyclerview.MultiItemTypeAdapter
import kotlinx.android.synthetic.main.activity_movie_detail.*
import org.jetbrains.anko.ctx


class MovieDetailActivity : BaseActivity<MovieDetailPresenter>(), MovieDetailContract.View {
    var id = ""
    var listCast: ArrayList<Casts>? = null
    var castAdapter: CastAdapter? = null
    override fun showLoading() {
    }

    override fun showError(msg: String) {
    }

    override fun showComplete() {
    }

    override fun showData(data: MovieDetail) {
        data.casts?.let { listCast!!.addAll(it) }
        castAdapter?.notifyDataSetChanged()
        summary.text = data.summary
        count.text = "共" + data.collect_count + "人看过  获得" + data.reviews_count + "条评论"
    }

    override fun initView() {
        val movieDetail = intent.getParcelableExtra<MovieDetail>("movieDetail")
        id = movieDetail.id!!
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        toolbar.setNavigationOnClickListener {
            finishAfterTransition()
        }
        mPresenter?.requestData(id)
        app_bar_layout.addOnOffsetChangedListener({ appBarLayout, verticalOffset ->
            if (Math.abs(verticalOffset) >= appBarLayout.totalScrollRange) {
                image.visibility = View.INVISIBLE
            } else {
                image.visibility = View.VISIBLE
            }
        })
        toolbar.title = movieDetail.title
        Glide.with(this).load(movieDetail?.images?.large).into(image)
        listCast = ArrayList()
        castAdapter = CastAdapter(this, R.layout.item_cast, listCast!!)
        casts.adapter = castAdapter
        casts.layoutManager = LinearLayoutManager(ctx, LinearLayoutManager.HORIZONTAL, false)
        casts.isNestedScrollingEnabled = false
        castAdapter?.setOnItemClickListener(object : MultiItemTypeAdapter.OnItemClickListener {

            override fun onItemClick(view: View?, holder: RecyclerView.ViewHolder?, position: Int) {
                CastActivity.start(this@MovieDetailActivity, listCast!![position], Pair.create(view?.findViewById(R.id.cast_head), "cast_head"), Pair.create(view?.findViewById(R.id.cast_name), "cast_name"))
            }

            override fun onItemLongClick(view: View?, holder: RecyclerView.ViewHolder?, position: Int): Boolean {
                return false
            }
        })
    }

    override fun generateLayoutId(): Int {
        return R.layout.activity_movie_detail
    }

    override fun generatePresenter(): MovieDetailPresenter {
        return MovieDetailPresenter(this)
    }

    override fun onBackPressed() {
        // super.onBackPressed()
        finishAfterTransition()
    }

    companion object {
        fun start(ctx: Activity, view: View, detail: MovieDetail) {
            val compat = ActivityOptionsCompat.makeSceneTransitionAnimation(ctx, view, "image")
            val intent = Intent()
            intent.setClass(ctx, MovieDetailActivity::class.java)
            intent.putExtra("movieDetail", detail)
            ActivityCompat.startActivity(ctx, intent, compat.toBundle())
        }
    }
}
