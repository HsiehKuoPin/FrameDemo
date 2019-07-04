package com.onechat.cat.ui.web

import android.content.Context
import android.os.Bundle
import com.benjamin.base.BaseActivity
import com.benjamin.utils.eighteen.ActivityUtils
import com.onechat.cat.R
import kotlinx.android.synthetic.main.activity_web.*

class WebActivity : BaseActivity() {
    override fun getLayoutId(): Int {
        return R.layout.activity_web
    }

    override fun initView() {
        super.initView()
        webView.loadUrl(intent.getStringExtra(KEY_URL))
        webView.setWebViewChangeTitleCallback { titleBarView?.title = it }
    }

    override fun onBackPressed() {
        if (webView.canGoBack()) webView.goBack()
        else super.onBackPressed()
    }

    companion object {
        const val KEY_URL = "key_url"
        fun start(context: Context, url: String) {
            ActivityUtils.launchActivity(context, WebActivity::class.java, Bundle().apply { putString(KEY_URL, url) })

        }
    }
}
