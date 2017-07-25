package com.zhangrui.huijukt.activity

import android.animation.Animator
import android.animation.ValueAnimator
import android.os.Build
import android.os.Bundle
import android.support.v4.view.PagerAdapter
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.view.ViewGroup
import android.view.ViewTreeObserver
import android.view.WindowManager
import com.bumptech.glide.Glide
import com.wingsofts.dragphotoview.DragPhotoView
import com.zhangrui.huijukt.R
import kotlinx.android.synthetic.main.activity_image.*
import org.jetbrains.anko.ctx

class ImageActivity : AppCompatActivity() {

    private var mPhotoViews: ArrayList<DragPhotoView>? = null

    var mOriginLeft: Int = 0
    var mOriginTop: Int = 0
    var mOriginHeight: Int = 0
    var mOriginWidth: Int = 0
    var mOriginCenterX: Int = 0
    var mOriginCenterY: Int = 0
    private var mTargetHeight: Float = 0.toFloat()
    private var mTargetWidth: Float = 0.toFloat()
    private var mScaleX: Float = 0.toFloat()
    private var mScaleY: Float = 0.toFloat()
    private var mTranslationX: Float = 0.toFloat()
    private var mTranslationY: Float = 0.toFloat()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        setContentView(R.layout.activity_image)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.statusBarColor = resources.getColor(R.color.colorPrimary)
        }
        mPhotoViews = ArrayList<DragPhotoView>()
        var url = intent.getStringExtra("url")
        var dragView = DragPhotoView(ctx)
        Glide.with(ctx)
                .load(url)
                .into(dragView);
        mPhotoViews!!.add(dragView)
        for (i in mPhotoViews!!.indices) {
            mPhotoViews!![i].setOnTapListener { finishWithAnimation() }
            mPhotoViews!![i].setOnExitListener { view, x, y, w, h -> performExitAnimation(view, x, y, w, h) }
        }

        fix_view_pager!!.adapter = object : PagerAdapter() {
            override fun getCount(): Int {
                return mPhotoViews!!.size
            }

            override fun instantiateItem(container: ViewGroup, position: Int): Any {
                container.addView(mPhotoViews!![position])
                return mPhotoViews!![position]
            }

            override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
                container.removeView(mPhotoViews!![position])
            }

            override fun isViewFromObject(view: View, `object`: Any): Boolean {
                return view === `object`
            }
        }

        fix_view_pager!!.viewTreeObserver
                .addOnGlobalLayoutListener(object : ViewTreeObserver.OnGlobalLayoutListener {
                    override fun onGlobalLayout() {
                        fix_view_pager!!.viewTreeObserver.removeOnGlobalLayoutListener(this)

                        mOriginLeft = intent.getIntExtra("left", 0)
                        mOriginTop = intent.getIntExtra("top", 0)
                        mOriginHeight = intent.getIntExtra("height", 0)
                        mOriginWidth = intent.getIntExtra("width", 0)
                        mOriginCenterX = mOriginLeft + mOriginWidth / 2
                        mOriginCenterY = mOriginTop + mOriginHeight / 2

                        val location = IntArray(2)

                        val photoView = mPhotoViews!![0]
                        photoView.getLocationOnScreen(location)

                        mTargetHeight = photoView.height.toFloat()
                        mTargetWidth = photoView.width.toFloat()
                        mScaleX = mOriginWidth.toFloat() / mTargetWidth
                        mScaleY = mOriginHeight.toFloat() / mTargetHeight

                        val targetCenterX = location[0] + mTargetWidth / 2
                        val targetCenterY = location[1] + mTargetHeight / 2

                        mTranslationX = mOriginCenterX - targetCenterX
                        mTranslationY = mOriginCenterY - targetCenterY
                        photoView.translationX = mTranslationX
                        photoView.translationY = mTranslationY

                        photoView.scaleX = mScaleX
                        photoView.scaleY = mScaleY

                        performEnterAnimation()

                        for (i in mPhotoViews!!.indices) {
                            mPhotoViews!![i].minScale = mScaleX
                        }
                    }
                })
    }

    /**
     * ===================================================================================
     *
     *
     * 底下是低版本"共享元素"实现   不需要过分关心  如有需要 可作为参考.
     *
     *
     * Code  under is shared transitions in all android versions implementation
     */
    private fun performExitAnimation(view: DragPhotoView, x: Float, y: Float, w: Float, h: Float) {
        view.finishAnimationCallBack()
        val viewX = mTargetWidth / 2 + x - mTargetWidth * mScaleX / 2
        val viewY = mTargetHeight / 2 + y - mTargetHeight * mScaleY / 2
        view.x = viewX
        view.y = viewY
        val centerX = view.x + mOriginWidth / 2
        val centerY = view.y + mOriginHeight / 2
        val translateX = mOriginCenterX - centerX
        val translateY = mOriginCenterY - centerY
        val translateXAnimator = ValueAnimator.ofFloat(view.x, view.x + translateX)
        translateXAnimator.addUpdateListener { valueAnimator -> view.x = valueAnimator.animatedValue as Float }
        translateXAnimator.duration = 300
        translateXAnimator.start()
        val translateYAnimator = ValueAnimator.ofFloat(view.y, view.y + translateY)
        translateYAnimator.addUpdateListener { valueAnimator -> view.y = valueAnimator.animatedValue as Float }
        translateYAnimator.addListener(object : Animator.AnimatorListener {
            override fun onAnimationStart(animator: Animator) {

            }
            override fun onAnimationEnd(animator: Animator) {
                animator.removeAllListeners()
                finish()
                overridePendingTransition(0, 0)
            }

            override fun onAnimationCancel(animator: Animator) {

            }
            override fun onAnimationRepeat(animator: Animator) {

            }
        })
        translateYAnimator.duration = 300
        translateYAnimator.start()
    }

    private fun finishWithAnimation() {

        val photoView = mPhotoViews!![0]
        val translateXAnimator = ValueAnimator.ofFloat(0f, mTranslationX)
        translateXAnimator.addUpdateListener { valueAnimator -> photoView.x = valueAnimator.animatedValue as Float }
        translateXAnimator.duration = 300
        translateXAnimator.start()

        val translateYAnimator = ValueAnimator.ofFloat(0f, mTranslationY)
        translateYAnimator.addUpdateListener { valueAnimator -> photoView.y = valueAnimator.animatedValue as Float }
        translateYAnimator.duration = 300
        translateYAnimator.start()

        val scaleYAnimator = ValueAnimator.ofFloat(1f, mScaleY)
        scaleYAnimator.addUpdateListener { valueAnimator -> photoView.scaleY = valueAnimator.animatedValue as Float }
        scaleYAnimator.duration = 300
        scaleYAnimator.start()

        val scaleXAnimator = ValueAnimator.ofFloat(1f, mScaleX)
        scaleXAnimator.addUpdateListener { valueAnimator -> photoView.scaleX = valueAnimator.animatedValue as Float }

        scaleXAnimator.addListener(object : Animator.AnimatorListener {
            override fun onAnimationStart(animator: Animator) {

            }
            override fun onAnimationEnd(animator: Animator) {
                animator.removeAllListeners()
                finish()
                overridePendingTransition(0, 0)
            }

            override fun onAnimationCancel(animator: Animator) {

            }

            override fun onAnimationRepeat(animator: Animator) {

            }
        })
        scaleXAnimator.duration = 300
        scaleXAnimator.start()
    }

    private fun performEnterAnimation() {
        val photoView = mPhotoViews!![0]
        val translateXAnimator = ValueAnimator.ofFloat(photoView.x, 0f)
        translateXAnimator.addUpdateListener { valueAnimator -> photoView.x = valueAnimator.animatedValue as Float }
        translateXAnimator.duration = 300
        translateXAnimator.start()

        val translateYAnimator = ValueAnimator.ofFloat(photoView.y, 0f)
        translateYAnimator.addUpdateListener { valueAnimator -> photoView.y = valueAnimator.animatedValue as Float }
        translateYAnimator.duration = 300
        translateYAnimator.start()

        val scaleYAnimator = ValueAnimator.ofFloat(mScaleY, 1f)
        scaleYAnimator.addUpdateListener { valueAnimator -> photoView.scaleY = valueAnimator.animatedValue as Float }
        scaleYAnimator.duration = 300
        scaleYAnimator.start()

        val scaleXAnimator = ValueAnimator.ofFloat(mScaleX, 1f)
        scaleXAnimator.addUpdateListener { valueAnimator -> photoView.scaleX = valueAnimator.animatedValue as Float }
        scaleXAnimator.duration = 300
        scaleXAnimator.start()
    }

    override fun onBackPressed() {
        finishWithAnimation()
    }
}
