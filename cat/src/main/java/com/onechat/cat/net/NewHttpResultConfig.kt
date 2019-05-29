package com.onechat.cat.net

import com.benjamin.http.IHttpResultConfig

/**
 * @author  Ben
 * @date 2019/4/3
 */
class NewHttpResultConfig : IHttpResultConfig {
    override fun getCodeName(): String {
        return "errorCode"
    }

    override fun getMessageName(): String {
        return "errorMsg"
    }

    override fun getDataName(): String {
        return "data"
    }

    override fun getSuccessCode(): Int {
        return 0
    }
}