package com.zhangrui.huijukt.widget;

import android.graphics.PointF;
import android.graphics.RectF;
import android.widget.ImageView;

import com.bm.library.Info;

import java.io.Serializable;

/**
 * Created by zhangrui on 2017/7/25.
 */

public class ImageInfo extends Info implements Serializable {
    public ImageInfo(RectF rect, RectF img, RectF widget, RectF base, PointF screenCenter, float scale, float degrees, ImageView.ScaleType scaleType) {
        super(rect, img, widget, base, screenCenter, scale, degrees, scaleType);
    }

}
