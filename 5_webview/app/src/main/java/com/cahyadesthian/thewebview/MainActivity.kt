package com.cahyadesthian.thewebview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.JsResult
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val webView = findViewById<WebView>(R.id.webView_mainUI)
        supportActionBar?.hide()
        webView.settings.javaScriptEnabled = true

//        webView.webViewClient = object : WebViewClient() {
//            override fun onPageFinished(view: WebView?, url: String?) {
//                Toast.makeText(this@MainActivity, "Your web already loaded \uD83C\uDF08 ", Toast.LENGTH_LONG).show()
//
//            }
//        }


        //model javascript
//        webView.webViewClient = object : WebViewClient() {
//
//            override fun onPageFinished(view: WebView?, url: String?) {
//                if (view != null) {
//                    view.loadUrl("javascript:alert('web already loaded \\uD83C\\uDF08')")
//                }
//            }
//
//        }
//        webView.webChromeClient = WebChromeClient()


        //model javascript langsung oke
        webView.webViewClient = object : WebViewClient() {

            override fun onPageFinished(view: WebView, url: String) {
                view.loadUrl("javascript:alert('web already loaded \\uD83C\\uDF08')")
            }

        }
        webView.webChromeClient = object : WebChromeClient() {

            override fun onJsAlert(
                view: WebView?,
                url: String?,
                message: String?,
                result: JsResult?
            ): Boolean {
                Toast.makeText(this@MainActivity, message, Toast.LENGTH_LONG).show()
                result?.confirm()
                return true
            }

        }



        webView.loadUrl("https://cahyadesthian.com/")
        //webView.loadUrl("https://www.dicoding.com")
    }



}