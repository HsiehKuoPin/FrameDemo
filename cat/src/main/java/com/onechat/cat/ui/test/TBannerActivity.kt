package com.onechat.cat.ui.test

import android.content.Context
import com.benjamin.base.BaseActivity
import com.benjamin.utils.StatusBarUtil
import com.benjamin.utils.eighteen.ActivityUtils
import com.onechat.cat.R
import com.onechat.cat.ui.test.banner.BannerImageLoader
import com.youth.banner.BannerConfig
import kotlinx.android.synthetic.main.activity_tbanner.*

class TBannerActivity : BaseActivity() {
    private val url: String ="https://vd.yinyuetai.com/hc.yinyuetai.com/uploads/videos/common/C89A016A1F017779225598F3E666BC34.mp4"
    private val url1: String ="http://183.60.197.29/19/w/w/w/p/wwwpsgqeeuqcccfsdwpgdqlibwulxd/hc.yinyuetai.com/5960016222A156A7E1E19DC7270A0CB0.mp4"
    private val url2: String ="http://183.60.197.26/5/e/v/n/f/evnfurhtwtbdcahruyzhuonhfiojxp/hc.yinyuetai.com/3453015E367E4D1FCBFF780F0E2D97DD.mp4"

    override fun getLayoutId(): Int {
        return R.layout.activity_tbanner
    }

    override fun initView() {
        super.initView()
        btn.setOnClickListener {
//            dataList.clear()
//            val proxy = CatApplication.getProxy(applicationContext)
//            val proxyUrl = proxy.getProxyUrl(url)
//            dataList.add("http://apigw.testv20.smartconns.com:9999/media/static/image/5c0f775c624d470001fd0ede")
//            dataList.add(proxyUrl)
//            dataList.add("http://apigw.testv20.smartconns.com:9999/media/static/image/5caf04fd624d4700013aa837")
//            banner.setImages(dataList)
//            banner.isAutoPlay(true)
//            banner.start()
            TestActivity.start(this)
        }
//        banner.isAutoPlay(false)
        banner.setDelayTime(2000)
        banner.setImageLoader(BannerImageLoader())
        banner.setIndicatorGravity(BannerConfig.CENTER)
        banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR)
    }

    private val dataList = mutableListOf<String>()
    override fun initData() {
        super.initData()
        dataList.add(url1)
        dataList.add("http://apigw.testv20.smartconns.com:9999/media/static/image/5c0f775c624d470001fd0ede")
        dataList.add(url)
        dataList.add(url1)
        dataList.add("http://apigw.testv20.smartconns.com:9999/media/static/image/5caf04fd624d4700013aa837")
        dataList.add(url1)
        dataList.add("http://apigw.testv20.smartconns.com:9999/media/static/image/5c0f775c624d470001fd0ede")
        banner.setImages(dataList)
        banner.isAutoPlay(true)
        banner.start()
    }

    override fun onResume() {
        super.onResume()
        StatusBarUtil.hideStatusNavigationBar(this.window)
//        banner.isAutoPlay(true)
//        banner.startAutoPlay()
    }

    override fun onPause() {
        super.onPause()
//        banner.isAutoPlay(false)
//        banner.stopAutoPlay()
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
