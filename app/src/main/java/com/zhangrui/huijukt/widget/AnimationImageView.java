package com.zhangrui.huijukt.widget;

import android.content.Context;
import android.util.AttributeSet;

import uk.co.senab.photoview.PhotoView;
import uk.co.senab.photoview.PhotoViewAttacher;

/**
 * Created by zhangrui on 2017/7/27.
 */

public class AnimationImageView extends PhotoView {

    private PhotoViewAttacher photoViewAttacher;
    public AnimationImageView(Context context) {
        super(context);
    }

    public AnimationImageView(Context context, AttributeSet attr) {
        super(context, attr);
    }

    public AnimationImageView(Context context, AttributeSet attr, int defStyle) {
        super(context, attr, defStyle);
    }

    public void setZoom(boolean zoom) {
        setZoomable(zoom);
    }
}
