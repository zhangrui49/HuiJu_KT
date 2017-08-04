package com.zhangrui.huijukt.activity

import android.app.Activity
import android.content.Intent
import android.support.v4.app.ActivityCompat
import android.support.v4.app.ActivityOptionsCompat
import android.support.v4.util.Pair
import android.support.v7.widget.RecyclerView
import android.view.View
import com.bumptech.glide.Glide
import com.zhangrui.huijukt.R
import com.zhangrui.huijukt.adapter.WorkAdapter
import com.zhangrui.huijukt.base.BaseActivity
import com.zhangrui.huijukt.bean.douban.Casts
import com.zhangrui.huijukt.bean.douban.Works
import com.zhangrui.huijukt.extensions.dismissProgress
import com.zhangrui.huijukt.extensions.showProgress
import com.zhangrui.huijukt.mvp.contract.CastContract
import com.zhangrui.huijukt.mvp.presenter.CastPresenter
import com.zhy.adapter.recyclerview.MultiItemTypeAdapter
import kotlinx.android.synthetic.main.activity_cast.*


class CastActivity : BaseActivity<CastPresenter>(), CastContract.View {
    var cast: Casts? = null
    var listMovie: ArrayList<Works>? = null
    var workAdapter: WorkAdapter? = null

    override fun showLoading() {
        showProgress()
    }

    override fun showError(msg: String) {
        dismissProgress()
    }

    override fun showComplete() {
        dismissProgress()
    }

    override fun showData(data: Casts) {
        dismissProgress()
        data.works?.let { listMovie!!.addAll(it) }
        workAdapter?.notifyDataSetChanged()
        works.scrollToPosition((listMovie?.size)!!.div(2))
        name.text = data.name
        name_en.text = data.name_en
        birth_place.text = data.born_place
        gender.text = data.gender
    }

    override fun initView() {
        listMovie = ArrayList()
        cast = intent.extras.getParcelable("cast")
        mPresenter?.requestData(cast?.id)
        workAdapter = WorkAdapter(this, R.layout.item_work, listMovie!!)
        works.adapter = workAdapter
        Glide.with(this).load(cast?.avatars?.large).into(image)
        workAdapter?.setOnItemClickListener(object : MultiItemTypeAdapter.OnItemClickListener {

            override fun onItemClick(view: View?, holder: RecyclerView.ViewHolder?, position: Int) {
                MovieDetailActivity.start(this@CastActivity, view?.findViewById(R.id.image)!!, listMovie!![position].subject!!)
            }

            override fun onItemLongClick(view: View?, holder: RecyclerView.ViewHolder?, position: Int): Boolean {
                return false
            }
        })
    }

    override fun generateLayoutId(): Int {
        return R.layout.activity_cast
    }

    override fun generatePresenter(): CastPresenter {
        return CastPresenter(this, this)
    }

    companion object {
        fun start(ctx: Activity, cast: Casts?, vararg sharedElements: Pair<View, String>) {
            val compat = ActivityOptionsCompat.makeSceneTransitionAnimation(ctx, *sharedElements)
            val intent = Intent()
            intent.setClass(ctx, CastActivity::class.java)
            intent.putExtra("cast", cast)
            ActivityCompat.startActivity(ctx, intent, compat.toBundle())
        }
    }

}
