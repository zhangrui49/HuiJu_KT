package com.zhangrui.huijukt.adapter

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Color
import android.media.Image
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
import com.zhangrui.huijukt.bean.meipai.Video
import com.zhangrui.huijukt.util.PaletteUtil
import com.zhy.adapter.recyclerview.CommonAdapter
import com.zhy.adapter.recyclerview.base.ViewHolder
import io.supercharge.shimmerlayout.ShimmerLayout
import org.jetbrains.anko.runOnUiThread
import java.util.*


/**
 *
 * Created by zhangrui on 2017/7/20.
 */
class MeipaiTabAdapter(context: Context, layout: Int, list: ArrayList<Video>) : CommonAdapter<Video>(context, layout, list) {

    var context: Context? = null
    var list: ArrayList<Video>? = null
    var inflater: LayoutInflater? = null

    init {
        this.context = context
        this.list = list
        this.inflater = LayoutInflater.from(context)
    }

    override fun convert(holder: ViewHolder?, video: Video?, position: Int) {
        holder?.setText(R.id.name, video?.screen_name)
        holder?.setText(R.id.play_count, video?.plays_count.toString() + "人播放")
        holder?.setText(R.id.like_count, video?.likes_count.toString() + "人喜欢")
        holder?.setText(R.id.comment_count, video?.comments_count.toString() + "人评论")
        holder?.setText(R.id.caption, video?.caption)
        Glide.with(mContext).load(video?.avatar).into(holder?.getView<ImageView>(R.id.head))
        Glide.with(mContext).load(video?.cover_pic).into(holder?.getView<ImageView>(R.id.cover))
    }

}