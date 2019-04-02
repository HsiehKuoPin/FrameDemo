package com.kuopin.frame.ui

import android.content.Context
import com.benjamin.base.BaseActivity
import com.benjamin.utils.eighteen.ActivityUtils
import com.kuopin.frame.R

class MainActivity : BaseActivity() {
    override fun getLayoutId(): Int {
        return R.layout.activity_main
    }

    companion object {
        fun start(context: Context) {
            ActivityUtils.launchActivity(context, MainActivity::class.java)
        }
    }
}
