package com.kuopin.frame.net

import com.benjamin.http.RetrofitManager

/**
 * @author Ben
 * @date 2019/3/19
 */
object NetProvider {

    val instance: APIService
        get() = RetrofitManager.create(APIService::class.java)

    fun getInstance(baseUrl: String): APIService {
        return RetrofitManager.create(APIService::class.java, baseUrl)
    }
}
