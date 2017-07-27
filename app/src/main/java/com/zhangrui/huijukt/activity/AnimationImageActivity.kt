package com.zhangrui.huijukt.activity

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.support.v4.app.ActivityOptionsCompat
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.view.WindowManager
import com.bumptech.glide.Glide
import com.zhangrui.huijukt.R
import kotlinx.android.synthetic.main.activity_animation_image.*
import org.jetbrains.anko.ctx

class AnimationImageActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        setContentView(R.layout.activity_animation_image)
        Glide.with(ctx)
                .load(intent.extras.get("image"))
                .into(image)
    }

    companion object {
        fun start(ctx: Activity, view: View, url: String) {
            val compat = ActivityOptionsCompat.makeSceneTransitionAnimation(ctx, view, "image")
            val intent = Intent()
            intent.setClass(ctx, AnimationImageActivity::class.java)
            intent.putExtra("image", url)
            ActivityCompat.startActivity(ctx, intent, compat.toBundle())
        }
    }
}
