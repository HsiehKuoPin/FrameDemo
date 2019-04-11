package com.onechat.cat.net

import com.benjamin.http.IHttpResultConfig

/**
 * @author  Ben
 * @date 2019/4/3
 */
class NewHttpResultConfig : IHttpResultConfig {
    override fun getCodeName(): String {
        return "resultCode"
    }

    override fun getMessageName(): String {
        return "resultDesc"
    }

    override fun getDataName(): String {
        return "content"
    }

    override fun getSuccessCode(): Int {
        return 0
    }
}