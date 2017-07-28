package com.zhangrui.huijukt.adapter

import android.content.Context
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import com.zhangrui.huijukt.R
import com.zhangrui.huijukt.bean.GankData
import com.zhangrui.huijukt.bean.douban.MovieDetail
import com.zhy.adapter.recyclerview.CommonAdapter
import com.zhy.adapter.recyclerview.base.ViewHolder
import com.bumptech.glide.Glide
import org.jetbrains.anko.doIfSdk


/**
 *
 * Created by zhangrui on 2017/7/20.
 */
class MovieTabAdapter(context: Context, layout: Int, list: ArrayList<MovieDetail>) : CommonAdapter<MovieDetail>(context, layout, list) {

    var context: Context? = null;
    var list: ArrayList<MovieDetail>? = null
    var inflater: LayoutInflater? = null

    init {
        this.context = context
        this.list = list
        this.inflater = LayoutInflater.from(context)
    }

    override fun convert(holder: ViewHolder?, movieDetail: MovieDetail?, position: Int) {
        Glide.with(mContext).load(movieDetail?.images?.medium).into(holder?.getView(R.id.image))
        holder?.setText(R.id.title, movieDetail?.title)
        holder?.setText(R.id.genres, movieDetail?.genres?.joinToString("/"))
        holder?.setText(R.id.year, movieDetail?.year)

        holder?.setText(R.id.director, movieDetail?.directors?.get(0)?.name)
        holder?.setRating(R.id.rating, movieDetail?.rating?.average!!)
    }


}