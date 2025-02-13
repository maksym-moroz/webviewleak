package com.example.webviewleak

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.webkit.WebView
import android.widget.Button

class MainActivity : Activity() {
    private lateinit var webView: WebView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity)

        webView = findViewById(R.id.webview)

        findViewById<Button>(R.id.recreate).setOnClickListener {
            val intent = Intent(this, SecondActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    override fun onDestroy() {
        webView.destroy()
        super.onDestroy()
    }
}
