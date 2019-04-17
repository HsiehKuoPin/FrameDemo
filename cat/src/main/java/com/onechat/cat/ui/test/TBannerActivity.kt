package com.onechat.cat.ui.test

import android.content.Context
import com.benjamin.base.BaseActivity
import com.benjamin.utils.StatusBarUtil
import com.benjamin.utils.eighteen.ActivityUtils
import com.onechat.cat.CatApplication
import com.onechat.cat.R
import com.onechat.cat.ui.test.banner.BannerImageLoader
import com.youth.banner.BannerConfig
import kotlinx.android.synthetic.main.activity_tbanner.*

class TBannerActivity : BaseActivity() {
    private val url: String =
        "https://vd.yinyuetai.com/hc.yinyuetai.com/uploads/videos/common/C89A016A1F017779225598F3E666BC34.mp4"

    override fun getLayoutId(): Int {
        return R.layout.activity_tbanner
    }

    override fun initView() {
        super.initView()
        btn.setOnClickListener {
            dataList.clear()
            val proxy = CatApplication.getProxy(applicationContext)
            val proxyUrl = proxy.getProxyUrl(url)
            dataList.add("http://apigw.testv20.smartconns.com:9999/media/static/image/5c0f775c624d470001fd0ede")
            dataList.add(proxyUrl)
            dataList.add("http://apigw.testv20.smartconns.com:9999/media/static/image/5caf04fd624d4700013aa837")
            banner.setImages(dataList)
            banner.start()
        }
        banner.isAutoPlay(false)
        banner.setDelayTime(5000)
        banner.setImageLoader(BannerImageLoader())
        banner.setIndicatorGravity(BannerConfig.CENTER)
        banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR)
    }

    private val dataList = mutableListOf<String>()
    override fun initData() {
        super.initData()
        dataList.add(url)
        dataList.add("http://apigw.testv20.smartconns.com:9999/media/static/image/5caf04fd624d4700013aa837")
        dataList.add("http://apigw.testv20.smartconns.com:9999/media/static/image/5c0f775c624d470001fd0ede")
        banner.setImages(dataList)
        banner.start()
    }

    override fun onResume() {
        super.onResume()
        StatusBarUtil.hideStatusNavigationBar(this.window)
        banner.isAutoPlay(true)
        banner.startAutoPlay()
    }

    override fun onPause() {
        super.onPause()
        banner.isAutoPlay(false)
        banner.stopAutoPlay()
    }

    override fun onDestroy() {
        banner.releaseBanner()
        super.onDestroy()
    }

    companion object {
        fun start(context: Context) {
            ActivityUtils.launchActivity(context, TBannerActivity::class.java)
        }
    }
}
