package com.onechat.cat.ui.web

import android.content.Context
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.benjamin.utils.eighteen.ActivityUtils
import com.onechat.cat.R
import kotlinx.android.synthetic.main.activity_web.*

class WebActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web)
        webView.loadUrl(intent.getStringExtra(KEY_URL))
    }

    companion object {
        const val KEY_URL = "key_url"
        fun start(context: Context, url: String) {
            ActivityUtils.launchActivity(context, WebActivity::class.java, Bundle().apply { putString(KEY_URL, url) })

        }
    }
}
