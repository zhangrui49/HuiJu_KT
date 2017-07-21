package com.zhangrui.huijukt.widget;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.ViewParent;
import android.widget.Toast;


/**
 * Created by zhangrui on 2017/7/20.
 */

public class FixRecyclerView extends RecyclerView implements GestureDetector.OnGestureListener {
    private float x;
    private float y;
    private GestureDetector detector;

    public FixRecyclerView(Context context) {
        super(context);
        detector = new GestureDetector(context, this);
    }

    public FixRecyclerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        detector = new GestureDetector(context, this);
    }

    public FixRecyclerView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        detector = new GestureDetector(context, this);
    }

//    @Override
//    public boolean dispatchTouchEvent(MotionEvent ev) {
//
//        switch (ev.getAction()) {
//            case MotionEvent.ACTION_DOWN:
//                x = ev.getRawX();
//                y = ev.getRawY();
//                break;
//            case MotionEvent.ACTION_MOVE:
//                if ((ev.getRawX()-x )>= (ev.getRawY()-y)) {
//                    return true;
//                } else {
//                    while (!(getParent() instanceof ViewPager))
//
//                        getParent().requestDisallowInterceptTouchEvent(true);
//                    return super.dispatchTouchEvent(ev);
//                }
//
//            case MotionEvent.ACTION_UP:
//                break;
//        }
//        return super.dispatchTouchEvent(ev);
//
//    }


//    @Override
//    public boolean onTouchEvent(MotionEvent e) {
//        return detector.onTouchEvent(e);
//    }

    @Override
    public boolean onGenericMotionEvent(MotionEvent event) {
        return super.onGenericMotionEvent(event);
    }

    @Override
    public boolean onDown(MotionEvent e) {
        return false;
    }

    @Override
    public void onShowPress(MotionEvent e) {

    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        Log.e("view","x"+distanceX+"");
        Log.e("view","y"+distanceY+"");

        return false;
    }

    @Override
    public void onLongPress(MotionEvent e) {

    }
//
//    @Override
//    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
//        float minMove = 120; // 最小滑动距离
//        float minVelocity = 0; // 最小滑动速度
//        float beginX = e1.getX();
//        float endX = e2.getX();
//        float beginY = e1.getY();
//        float endY = e2.getY();
//
//        if (beginX - endX > minMove && Math.abs(velocityX) > minVelocity) { // 左滑
//            return true;
//        } else if (endX - beginX > minMove && Math.abs(velocityX) > minVelocity) { // 右滑
//            return true;
//        } else if (beginY - endY > minMove && Math.abs(velocityY) > minVelocity) { // 上滑
//            while (!(getParent() instanceof ViewPager))
//                getParent().requestDisallowInterceptTouchEvent(true);
//            return true;
//        } else if (endY - beginY > minMove && Math.abs(velocityY) > minVelocity) { // 下滑
//            Toast.makeText(getContext(), velocityX + "下滑", Toast.LENGTH_SHORT).show();
//            while (!(getParent() instanceof ViewPager))
//                getParent().requestDisallowInterceptTouchEvent(true);
//            return true;
//        }
//        return false;
//    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        return false;
    }
}
