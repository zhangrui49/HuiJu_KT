package com.zhangrui.huijukt.util;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.os.Message;
import android.support.v4.content.ContextCompat;
import android.support.v7.graphics.Palette;

import com.zhangrui.huijukt.R;

import java.util.logging.Handler;

/**
 * Created by zhangrui on 2017/8/3.
 */

public class PaletteUtil implements Palette.PaletteAsyncListener {

    private PatternCallBack patternCallBack;

    public PaletteUtil(Bitmap bitmap,PatternCallBack patternCallBack) {
        this.patternCallBack = patternCallBack;
        Palette.from(bitmap).generate(this);
    }

    @Override
    public void onGenerated(Palette palette) {
        Palette.Swatch a = palette.getDominantSwatch();
        int color = palette.getDominantColor(Color.BLUE);
        if (a != null) {
            patternCallBack.onCallBack(colorEasy(color));
        }

    }

    /**
     * 创建Drawable对象
     *
     * @param RGBValues
     * @param two
     * @return
     */
    private Drawable changedImageViewShape(int RGBValues, int two) {
        if (two == 0) {
            two = colorEasy(RGBValues);
        } else {
            two = colorBurn(two);
        }
        GradientDrawable shape = new GradientDrawable(GradientDrawable.Orientation.TL_BR
                , new int[]{RGBValues, two});
        shape.setShape(GradientDrawable.RECTANGLE);
        //设置渐变方式
        shape.setGradientType(GradientDrawable.LINEAR_GRADIENT);
        //圆角
        shape.setCornerRadius(8);
        return shape;
    }


    /**
     * 颜色变浅处理
     *
     * @param RGBValues
     * @return
     */
    public static int colorEasy(int RGBValues) {
        int red = RGBValues >> 16 & 0xff;
        int green = RGBValues >> 8 & 0xff;
        int blue = RGBValues & 0xff;
        if (red == 0) {
            red = 10;
        }
        if (green == 0) {
            green = 10;
        }
        if (blue == 0) {
            blue = 10;
        }
        red = (int) Math.floor(red * (1 + 0.2));
        green = (int) Math.floor(green * (1 + 0.2));
        blue = (int) Math.floor(blue * (1 + 0.2));
        return Color.rgb(red, green, blue);
    }

    public static int reverseColor(int color) {
        int red = color >> 16 & 0xff;
        int green = color >> 8 & 0xff;
        int blue = color & 0xff;

        red = Math.abs(red - 255);
        green = Math.abs(green - 255);
        blue = Math.abs(blue - 255);
        return Color.rgb(red, green, blue);
    }

    /**
     * 颜色加深处理
     *
     * @param RGBValues
     * @return
     */
    public static int colorBurn(int RGBValues) {
        int red = RGBValues >> 16 & 0xff;
        int green = RGBValues >> 8 & 0xff;
        int blue = RGBValues & 0xff;
        red = (int) Math.floor(red * (1 - 0.1));
        green = (int) Math.floor(green * (1 - 0.1));
        blue = (int) Math.floor(blue * (1 - 0.1));
        return Color.rgb(red, green, blue);
    }


    public interface PatternCallBack {
        void onCallBack(int mainColor);
    }

}
