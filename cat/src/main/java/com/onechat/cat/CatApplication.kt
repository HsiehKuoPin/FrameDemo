package com.onechat.cat

import android.app.Application
import com.benjamin.http.HttpConfig
import com.benjamin.http.RetrofitManager
import com.benjamin.utils.eighteen.UtilsInitializer
import com.onechat.cat.net.NewHttpResultConfig
import com.onechat.cat.utils.Env
import com.orhanobut.logger.AndroidLogAdapter
import com.orhanobut.logger.Logger
import com.orhanobut.logger.PrettyFormatStrategy

/**
 * @author  Ben
 * @date 2019/4/3
 */
class CatApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        UtilsInitializer.init(this)
        initHttp()
        initLogger(Env.isNotRelease())
    }

    private fun initHttp() {
        val httpConfig = HttpConfig()
        httpConfig.baseUrl = if (Env.buildType() == Env.DEBUG) "http://www.ichatcat.com:8088/onechat/"
        else "http://apigw.jizhigou.smartconns.com:9999/"
        httpConfig.isOpenLogger = Env.buildType() == Env.DEBUG
        httpConfig.resultConfig = NewHttpResultConfig()
        httpConfig.timeOut = 10
//        httpConfig.setTokenProvider { SPUtil.getString(this, "key_token") }
        RetrofitManager.initConfig(httpConfig)
    }

    private fun initLogger(notRelease: Boolean) {
        val formatStrategy = PrettyFormatStrategy.newBuilder()
            .showThreadInfo(false)  // (Optional) Whether to show thread info or not. Default true
            .methodCount(1)         // (Optional) How many method line to show. Default 2
//                .methodOffset(0)        // (Optional) Hides internal method calls up to offset. Default 0
//                .logStrategy(customLog) // (Optional) Changes the log strategy to print out. Default LogCat
//            .tag("Cat")   // (Optional) Global tag for every log. Default PRETTY_LOGGER
            .build()
        Logger.addLogAdapter(object : AndroidLogAdapter(formatStrategy) {
            override fun isLoggable(priority: Int, tag: String?): Boolean {
                return notRelease || priority == Logger.ERROR
            }
        })
//        Logger.addLogAdapter(DiskLogAdapter())
    }
}