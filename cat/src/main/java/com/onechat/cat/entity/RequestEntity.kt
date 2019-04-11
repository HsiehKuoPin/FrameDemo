package com.onechat.cat.entity

import com.benjamin.utils.eighteen.EncryptUtils
import com.onechat.cat.BuildConfig
import com.onechat.cat.cache.AppCache

/**
 * @author  Ben
 * @date 2019/4/3
 */
class RequestEntity {
    private val appId: String = BuildConfig.APPLICATION_ID
    private val timestamp: Long = System.currentTimeMillis()
    private val encrypt: String
    private var memberUUID: String? = null
    var imei: String? = null
    var phone: String? = null
    var code: String? = null
    var accountType: String? = null//密码注册填password;验证码登录 code
    var password: String? = null
    var channelCode: String? = null
    var inviterUUID: String? = null//邀请者的 uuid
    var resources: String? = null//资源的来源渠道：1 表示热门推荐 2 表示新鲜

    init {
        encrypt = EncryptUtils.encryptMD5ToString(appId + "1234" + timestamp)
        memberUUID = AppCache.memberUUID
    }
}
