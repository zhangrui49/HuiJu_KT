package com.zhangrui.huijukt.activity

import android.content.Context
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.webkit.WebChromeClient
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import com.zhangrui.huijukt.R
import kotlinx.android.synthetic.main.activity_web.*
import android.view.ViewGroup
import android.support.v4.content.ContextCompat.startActivity
import android.content.Intent
import android.net.Uri
import android.util.Log
import java.util.regex.Pattern


class WebActivity : AppCompatActivity() {
    var imageUrls: Array<String> = emptyArray();

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web)
        webview.getSettings().setJavaScriptEnabled(true);
        webview.getSettings().setBuiltInZoomControls(true);
        webview.getSettings().setDisplayZoomControls(false);
        webview.getSettings().setSupportZoom(true);
        webview.getSettings().setDomStorageEnabled(true);
        webview.getSettings().setDatabaseEnabled(true);
        webview.getSettings().setLoadWithOverviewMode(true);
        webview.getSettings().setUseWideViewPort(true);
        webview.addJavascriptInterface(JavascriptInterface(this, imageUrls), "imagelistener")
        setSupportActionBar(toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        toolbar.setNavigationOnClickListener {
            finish()
        }
        var url = intent.getStringExtra("url")
        webview.loadUrl(url);
        webview.setWebChromeClient(object : WebChromeClient() {
            override fun onProgressChanged(view: WebView?, newProgress: Int) {
                super.onProgressChanged(view, newProgress)
                if (newProgress == 100) {
                    progress.setVisibility(View.GONE);
                } else {
                    progress.setProgress(newProgress)
                    progress.setVisibility(View.VISIBLE);
                }
            }
        })
        webview.setWebViewClient(object : WebViewClient() {
            override fun shouldOverrideUrlLoading(view: WebView?, request: WebResourceRequest?): Boolean {
                if (request?.url.toString().startsWith("http")) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        view?.loadUrl(request?.getUrl().toString());
                    } else {
                        view?.loadUrl(request.toString());
                    }
                    return true;

                } else {
                    var uri = Uri.parse(request?.url.toString())
                    val intent = Intent(Intent.ACTION_VIEW, uri)
                    startActivity(intent)
                    return true;
                }
            }

            override fun onPageFinished(view: WebView?, url: String?) {
                super.onPageFinished(view, url)
                addImageClickListener(view);//待网页加载完全后设置图片点击的监听方法
            }
        })

    }

    override fun onDestroy() {
        if (webview != null) {
            (webview.getParent() as ViewGroup).removeView(webview)
            webview.destroy()
        }
        super.onDestroy()
    }

    override fun onBackPressed() {
        if (webview.canGoBack()) {
            webview.goBack()
        } else {
            super.onBackPressed()
        }
    }

    inner class JavascriptInterface(private val context: Context, private val imageUrls: Array<String>) {
        @android.webkit.JavascriptInterface
        fun openImage(img: String) {
            val intent = Intent()

            intent.putExtra("url", img)
            intent.setClass(context, ImageActivity::class.java)
            context.startActivity(intent)
        }
    }

    private fun addImageClickListener(webView: WebView?) {
        webView?.loadUrl("javascript:(function(){" +
                "var objs = document.getElementsByTagName(\"img\"); " +
                "for(var i=0;i<objs.length;i++)  " +
                "{"
                + "    objs[i].onclick=function()  " +
                "    {  "
                + "        window.imagelistener.openImage(this.src);  " + //通过js代码找到标签为img的代码块，设置点击的监听方法与本地的openImage方法进行连接

                "    }  " +
                "}" +
                "})()")
    }

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
}
