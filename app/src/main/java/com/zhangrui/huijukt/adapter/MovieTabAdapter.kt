package com.zhangrui.huijukt.adapter

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Color
import android.support.v7.widget.CardView
import android.util.Log
import android.view.LayoutInflater
import android.widget.ImageView
import android.widget.RatingBar
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.SimpleTarget
import com.bumptech.glide.request.transition.Transition
import com.lcodecore.tkrefreshlayout.utils.LogUtil
import com.zhangrui.huijukt.R
import com.zhangrui.huijukt.bean.douban.MovieDetail
import com.zhangrui.huijukt.util.PaletteUtil
import com.zhy.adapter.recyclerview.CommonAdapter
import com.zhy.adapter.recyclerview.base.ViewHolder
import io.supercharge.shimmerlayout.ShimmerLayout
import org.jetbrains.anko.runOnUiThread


/**
 *
 * Created by zhangrui on 2017/7/20.
 */
class MovieTabAdapter(context: Context, layout: Int, list: ArrayList<MovieDetail>) : CommonAdapter<MovieDetail>(context, layout, list) {

    var context: Context? = null
    var list: ArrayList<MovieDetail>? = null
    var inflater: LayoutInflater? = null

    init {
        this.context = context
        this.list = list
        this.inflater = LayoutInflater.from(context)
    }

    override fun convert(holder: ViewHolder?, movieDetail: MovieDetail?, position: Int) {
        //  Glide.with(mContext).load(movieDetail?.images?.large).into(holder?.getView(R.id.image))
        if (movieDetail?.mainColor == null) {

          //  holder?.getView<ShimmerLayout>(R.id.shimmer)?.startShimmerAnimation()
            Glide.with(mContext).asBitmap().load(movieDetail?.images?.large).into(object : SimpleTarget<Bitmap>() {
                override fun onResourceReady(resource: Bitmap?, transition: Transition<in Bitmap>?) {

                    PaletteUtil(resource, PaletteUtil.PatternCallBack { mainColor ->
                        movieDetail?.mainColor = mainColor
                        notifyItemChanged(position)
                    })
                }
            })
        } else {
          //  holder?.getView<ShimmerLayout>(R.id.shimmer)?.stopShimmerAnimation()
            Glide.with(mContext).load(movieDetail.images?.large).into(holder?.getView<ImageView>(R.id.image))
            holder?.getView<CardView>(R.id.movie_card)?.setBackgroundColor(movieDetail.mainColor!!)
            holder?.setText(R.id.title, movieDetail.title)
            holder?.setTextColor(R.id.title, PaletteUtil.colorBurn(PaletteUtil.reverseColor(movieDetail.mainColor!!)))
            holder?.setText(R.id.genres, movieDetail.genres?.joinToString("/"))
            holder?.setTextColor(R.id.genres, PaletteUtil.colorBurn(PaletteUtil.reverseColor(movieDetail.mainColor!!)))
            holder?.setText(R.id.year, movieDetail.year)
            holder?.setTextColor(R.id.year, PaletteUtil.colorBurn(PaletteUtil.reverseColor(movieDetail.mainColor!!)))
            holder?.setText(R.id.director, movieDetail.directors?.get(0)?.name)
            holder?.setTextColor(R.id.director, PaletteUtil.colorBurn(PaletteUtil.reverseColor(movieDetail.mainColor!!)))
            holder?.setRating(R.id.rating, movieDetail.rating?.average!!)
        }
    }

}