package com.kuopin.frame.net

import com.kuopin.frame.entity.DeviceEntity
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

/**
 * @author  Ben
 * @date 2019/3/19
 */
interface APIService {

    /**
     * 获取设备信息
     */
    @Headers("Cache: null", "Token: null", "Params: null")
    @GET("erp/device")
    fun getDevice(@Query("macAddress") macAddress: String): Observable<DeviceEntity>
}