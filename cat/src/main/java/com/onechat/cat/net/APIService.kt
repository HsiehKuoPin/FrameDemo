package com.onechat.cat.net

import com.onechat.cat.entity.MemberEntity
import com.onechat.cat.entity.RecommendEntity
import com.onechat.cat.entity.RequestEntity
import io.reactivex.Observable
import retrofit2.http.Body
import retrofit2.http.POST

/**
 * @author  Ben
 * @date 2019/4/3
 */
interface APIService{

    @POST("member/register")
    fun register(@Body requestBody: RequestEntity): Observable<MemberEntity>

    @POST("member/login")
    fun login(@Body requestBody: RequestEntity): Observable<MemberEntity>

    @POST("index/recommend")
    fun recommend(@Body requestBody: RequestEntity): Observable<RecommendEntity>

}