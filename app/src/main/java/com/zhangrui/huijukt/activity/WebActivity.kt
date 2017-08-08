package com.zhangrui.huijukt.activity

import android.annotation.SuppressLint
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.zhangrui.huijukt.R
import kotlinx.android.synthetic.main.activity_web.*
import android.view.ViewGroup
import android.content.Intent
import android.net.Uri
import android.util.Log
import android.webkit.*
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayer
import org.jetbrains.anko.ctx
import java.io.ByteArrayInputStream
import java.util.regex.Pattern
import fm.jiecao.jcvideoplayer_lib.JCUtils
import android.os.Handler
import android.os.Message
import android.widget.AbsoluteLayout
import com.bumptech.glide.Glide
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayerStandard
import android.widget.Toast


class WebActivity : AppCompatActivity() {
    var imageUrls: Array<String> = emptyArray()

    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web)
        webview.settings.javaScriptEnabled = true
        webview.settings.builtInZoomControls = true
        webview.settings.displayZoomControls = false
        webview.settings.setSupportZoom(true)
        webview.settings.domStorageEnabled = true
        webview.settings.databaseEnabled = true
        webview.settings.loadWithOverviewMode = true
        webview.settings.useWideViewPort = true
        webview.addJavascriptInterface(JavascriptInterface(), "imagelistener")
        setSupportActionBar(toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        toolbar.setNavigationOnClickListener {
            finish()
        }
        val loadUrl = intent.getStringExtra("url")
        webview.loadUrl(loadUrl)
        webview.setWebChromeClient(object : WebChromeClient() {
            override fun onProgressChanged(view: WebView?, newProgress: Int) {
                super.onProgressChanged(view, newProgress)
                if (newProgress == 100) {
                    progress.visibility = View.GONE
                } else {
                    progress.progress = newProgress
                    progress.visibility = View.VISIBLE
                }
            }

            override fun onReceivedTitle(view: WebView?, title: String?) {
                super.onReceivedTitle(view, title)
                toolbar.title = title
            }
        })
        webview.setWebViewClient(object : WebViewClient() {
            override fun shouldOverrideUrlLoading(view: WebView?, request: WebResourceRequest?): Boolean {
                if (request?.url.toString().startsWith("http")) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        view?.loadUrl(request?.url.toString())
                    } else {
                        view?.loadUrl(request.toString())
                    }
                    return true

                } else {
                    try {
                        val uri = Uri.parse(request?.url.toString())
                        val intent = Intent(Intent.ACTION_VIEW, uri)
                        startActivity(intent)
                        Log.e("webview", uri.toString())
                        return true
                    } catch(e: Exception) {
                        Log.e("webview", e.localizedMessage)
                        return true
                    }
                }
            }

            override fun onPageFinished(view: WebView?, url: String?) {
                super.onPageFinished(view, url)
                // addImageClickListener(view)//待网页加载完全后设置图片点击的监听方法
            }
//
//            override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
//                if (url != null && url.startsWith("http")) {
//
//                    view?.loadUrl(url.toString())
//
//                    return true
//
//                } else {
//                    try {
//                        val uri = Uri.parse(url.toString())
//                        val intent = Intent(Intent.ACTION_VIEW, uri)
//                        startActivity(intent)
//                        Log.e("webview", uri.toString())
//                        return true
//                    } catch(e: Exception) {
//                        Log.e("webview", e.localizedMessage)
//                        return true
//                    }
//                }
//            }

            override fun shouldInterceptRequest(view: WebView?, request: WebResourceRequest?): WebResourceResponse? {

                if (request != null && request.url.toString().endsWith("mp4", true)) {
                    Log.e("webview", request.url.toString())
                    mHandler.sendMessage(Message.obtain(mHandler, 0, request.url.toString()))
                    val result: String = "<html>\n" +
                            "<title>谷歌</title>\n" +
                            "<body>\n" +
                            "<a href=\"www.google.com\">谷歌</a>反正你也访问不了\n" +
                            "</body>\n" +
                            "<html>"
                    val response = WebResourceResponse("text/html",
                            "utf-8",
                            ByteArrayInputStream(result.toByteArray()))
                    response.setStatusCodeAndReasonPhrase(404, "404")
                    return response
                } else {
                    return null
                }
            }
        })
        webview.setDownloadListener { url, _, _, _, _ ->
            val uri = Uri.parse(url)
            val intent = Intent(Intent.ACTION_VIEW, uri)
            startActivity(intent)
        }
    }

    override fun onDestroy() {
        if (webview != null) {
            (webview.parent as ViewGroup).removeView(webview)
            webview.destroy()
        }
        super.onDestroy()
    }

    override fun onResume() {
        super.onResume()
        webview.onResume()
        webview.resumeTimers()
    }

    override fun onPause() {
        super.onPause()
        webview.onPause()
        webview.pauseTimers()
        JCVideoPlayer.releaseAllVideos();
    }

    override fun onBackPressed() {
        if (JCVideoPlayer.backPress()) {
            return
        }
        if (webview.canGoBack()) {
            webview.goBack()
        } else {
            super.onBackPressed()
        }
    }

    inner class JavascriptInterface {
        @android.webkit.JavascriptInterface
        fun openImage(img: String) {
            val intent = Intent()
            intent.putExtra("url", img)
            intent.setClass(ctx, ImageActivity::class.java)
            startActivity(intent)
        }

        @android.webkit.JavascriptInterface
        fun adViewJieCaoVideoPlayer(title: String, videoUrl: String, thumbUrl: String, width: Int, height: Int, top: Int, left: Int) {
            runOnUiThread {
                val webVideo = JCVideoPlayerStandard(this@WebActivity)
                webVideo.setUp(videoUrl,
                        JCVideoPlayer.SCREEN_LAYOUT_LIST, title)
                Glide.with(this@WebActivity)
                        .load(thumbUrl)
                        .into(webVideo.thumbImageView)
                val ll = ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
                val layoutParams = AbsoluteLayout.LayoutParams(ll)
                layoutParams.y = JCUtils.dip2px(this@WebActivity, top.toFloat())
                layoutParams.x = JCUtils.dip2px(this@WebActivity, left.toFloat())
                layoutParams.height = JCUtils.dip2px(this@WebActivity, height.toFloat())
                layoutParams.width = JCUtils.dip2px(this@WebActivity, width.toFloat())
                webview.addView(webVideo, layoutParams)
            }
        }
    }
//        private fun addImageClickListener(webView: WebView?) {
//            webView?.loadUrl("javascript:(function(){" +
//                    "var objs = document.getElementsByTagName(\"img\"); " +
//                    "for(var i=0;i<objs.length;i++)  " +
//                    "{"
//                    + "    objs[i].onclick=function()  " +
//                    "    {  "
//                    + "        window.imagelistener.openImage(this.src);  " + //通过js代码找到标签为img的代码块，设置点击的监听方法与本地的openImage方法进行连接
//
//                    "    }  " +
//                    "}" +
//                    "})()")
//        }

    fun returnImageUrlsFromHtml(htmlCode: String): Array<String>? {
        val imageSrcList = ArrayList<String>()
        val p = Pattern.compile("<img\\b[^>]*\\bsrc\\b\\s*=\\s*('|\")?([^'\"\n\r>]+(\\.jpg|\\.bmp|\\.eps|\\.gif|\\.mif|\\.miff|\\.png|\\.tif|\\.tiff|\\.svg|\\.wmf|\\.jpe|\\.jpeg|\\.dib|\\.ico|\\.tga|\\.cut|\\.pic)\\b)[^>]*>", Pattern.CASE_INSENSITIVE)
        val m = p.matcher(htmlCode)
        var quote: String? = null
        var src = ""
        while (m.find()) {
            quote = m.group(1)
            src = if (quote == null || quote.trim { it <= ' ' }.isEmpty()) m.group(2).split("//s+")[0] else m.group(2)
            imageSrcList.add(src)
        }
        if (imageSrcList.size == 0) {
            Log.e("imageSrcList", "资讯中未匹配到图片链接")
            return null
        }
        return imageSrcList.toTypedArray()
    }


    private val mHandler = @SuppressLint("HandlerLeak")
    object : Handler() {
        override fun handleMessage(msg: Message) {
            super.handleMessage(msg)
            when (msg.what) {
                0 -> {
                    JCVideoPlayerStandard.startFullscreen(this@WebActivity, JCVideoPlayerStandard::class.java, msg.obj.toString(), title)
                }
                1 -> {

                }
            }
        }
    }
}
