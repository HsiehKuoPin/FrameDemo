package com.onechat.cat.ui.test.video

import android.content.Context
import com.benjamin.base.BaseActivity
import com.benjamin.utils.StatusBarUtil
import com.benjamin.utils.eighteen.ActivityUtils
import com.onechat.cat.R

class BannerActivity : BaseActivity() {
    override fun getLayoutId(): Int {
        return R.layout.activity_banner
    }

    override fun onStart() {
        super.onStart()
        StatusBarUtil.hideStatusNavigationBar(this.window)
    }


    companion object {
        fun start(context: Context) {
            ActivityUtils.launchActivity(context, BannerActivity::class.java)
        }
    }
}
