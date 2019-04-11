package com.onechat.cat.net

import com.benjamin.http.RetrofitManager

/**
 * @author  Ben
 * @date 2019/4/9
 */
object NetProvider {

    val instance: APIService
        get() = RetrofitManager.create(APIService::class.java)

    fun getInstance(baseUrl: String): APIService {
        return RetrofitManager.create(APIService::class.java, baseUrl)
    }
}