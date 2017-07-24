package com.zhangrui.huijukt.adapter

import com.bumptech.glide.Glide
import com.zhangrui.huijukt.R
import com.zhangrui.huijukt.bean.GankDay
import com.zhy.adapter.recyclerview.base.ItemViewDelegate
import com.zhy.adapter.recyclerview.base.ViewHolder

/**
 *
 * Created by zhangrui on 2017/7/24.
 */
class GankDayAdapter {
    class TitleItemDelegate : ItemViewDelegate<GankDay.Results.Return> {

        override fun getItemViewLayoutId(): Int {
            return R.layout.item_gank_day_title
        }

        override fun convert(holder: ViewHolder?, t: GankDay.Results.Return?, position: Int) {
            holder?.setText(R.id.title, t?.desc)
        }

        override fun isForViewType(item: GankDay.Results.Return?, position: Int): Boolean {
            return item?.type.equals("title")
        }

    }

    class ImageItemDelegate : ItemViewDelegate<GankDay.Results.Return> {
        override fun getItemViewLayoutId(): Int {
            return R.layout.item_gank_img
        }

        override fun convert(holder: ViewHolder?, t: GankDay.Results.Return?, position: Int) {
            Glide.with(holder?.convertView?.context)
                    .load(t?.url)
                    .into(holder!!.getView(R.id.image));
        }

        override fun isForViewType(item: GankDay.Results.Return?, position: Int): Boolean {
            return item?.type.equals("福利")
        }
    }

    class ContentItemDelegate : ItemViewDelegate<GankDay.Results.Return> {
        override fun getItemViewLayoutId(): Int {
            return R.layout.item_gank_data
        }

        override fun convert(holder: ViewHolder?, t: GankDay.Results.Return?, position: Int) {
            holder?.setText(R.id.desc, t?.desc)
            holder?.setText(R.id.time, t?.publishedAt)
            holder?.setText(R.id.who, t?.who)
        }

        override fun isForViewType(item: GankDay.Results.Return?, position: Int): Boolean {
            return (!item?.type.equals("福利")) && (!item?.type.equals("title"))
        }
    }
}