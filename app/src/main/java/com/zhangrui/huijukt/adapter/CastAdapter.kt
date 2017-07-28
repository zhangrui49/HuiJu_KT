package com.zhangrui.huijukt.adapter

import android.content.Context
import android.view.LayoutInflater
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.wingsofts.dragphotoview.DragPhotoView
import com.zhangrui.huijukt.R
import com.zhangrui.huijukt.bean.GankData
import com.zhangrui.huijukt.bean.douban.Avatars
import com.zhangrui.huijukt.bean.douban.Casts
import com.zhy.adapter.recyclerview.CommonAdapter
import com.zhy.adapter.recyclerview.base.ViewHolder
import org.jetbrains.anko.coroutines.experimental.asReference
import uk.co.senab.photoview.PhotoView


/**
 * Created by zhangrui on 2017/7/14.
 */
class CastAdapter(context: Context, layout: Int, list: ArrayList<Casts>) : CommonAdapter<Casts>(context, layout, list) {
    var context: Context? = null;
    var list: ArrayList<Casts>? = null
    var inflater: LayoutInflater? = null

    init {
        this.context = context
        this.list = list
        this.inflater = LayoutInflater.from(context)
    }

    override fun convert(holder: ViewHolder?, t: Casts?, position: Int) {
        val image = holder?.getView<ImageView>(R.id.cast_head)
        Glide.with(context)
                .load(list!![position].avatars?.large)
                .into(image)
        holder?.setText(R.id.cast_name, t?.name)
    }

}