package com.tanyasawit

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.KeyEvent
import android.view.KeyEvent.ACTION_UP
import android.view.KeyEvent.KEYCODE_BACK
import android.view.View
import android.webkit.*
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        webview?.webViewClient = object : WebViewClient() {

            override fun onPageFinished(view: WebView?, url: String?) {
                super.onPageFinished(view, url)
                if (url.toString().contains("https://api.whatsapp.com/send/?phone=6281222301124"))
                {
                    webview.webChromeClient = object :WebChromeClient(){

                    }
                }
            }

            override fun onReceivedError(
                view: WebView?,
                request: WebResourceRequest?,
                error: WebResourceError?
            ) {
                super.onReceivedError(view, request, error)
                Log.e("TAG","error ${error.toString()}")
                Log.e("TAG","error ${request.toString()}")
            }
            override fun onLoadResource(view: WebView?, url: String?) {
                super.onLoadResource(view, url)
                if (url.toString().contains("https://api.whatsapp.com/send/?phone=6281222301124"))
                {
                    webview.webChromeClient = object :WebChromeClient(){

                    }
                }

            }

            override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
                if (url.toString().startsWith("whatsapp:")) {
                    startActivity(
                        Intent(
                            Intent.ACTION_VIEW,
                            Uri.parse(
                               url
                            )
                        )
                    )
                    return true
                }
                return false
            }



        }
        webview?.settings?.setJavaScriptEnabled(true);
        webview?.setScrollBarStyle(View.SCROLLBARS_OUTSIDE_OVERLAY);

        webview?.getSettings()?.setBuiltInZoomControls(true);
        webview?.getSettings()?.setUseWideViewPort(true);
        webview?.getSettings()?.setLoadWithOverviewMode(true);

        webview.loadUrl("http://web.rsudcilincing.id/");
        Handler().postDelayed({
            progress_circular.visibility = View.GONE
        },6000)



    }



    override fun onBackPressed() {
        super.onBackPressed()
        if (webview.canGoBack()){
            webview.goBack()
        } else{
            super.onBackPressed()
        }
    }

    override fun onKeyUp(keyCode: Int, event: KeyEvent?): Boolean {
        if (event?.action != ACTION_UP || event.keyCode != KEYCODE_BACK) {
            return super.onKeyUp(keyCode, event)
        }
        if (webview.canGoBack()) {
            webview.goBack()
        } else {
            finish()
        }
        return true
    }




}