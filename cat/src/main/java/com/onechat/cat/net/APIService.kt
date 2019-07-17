package com.onechat.cat.net

import com.onechat.cat.entity.*
import io.reactivex.Observable
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

/**
 * @author  Ben
 * @date 2019/4/3
 */
interface APIService {

    @POST("member/register")
    fun register(@Body requestBody: RequestEntity): Observable<MemberEntity>

    @POST("member/login")
    fun login(@Body requestBody: RequestEntity): Observable<MemberEntity>

    @POST("index/recommend")
    fun recommend(@Body requestBody: RequestEntity): Observable<RecommendEntity>

    //----------------玩Android 开放API(https://www.wanandroid.com/index)--------------------

    /**
     * 获取公众号列表
     */
    @GET("wxarticle/chapters/json")
    fun getAccounts(): Observable<List<AccountEntity>>

    @GET("wxarticle/list/{id}/{curPage}/json")
    fun getAccountArticle(@Path("id") id: Int,
                           @Path("curPage") curPage: Int): Observable<AccountArticleEntity>
    /**
     * 首页banner
     */
    @GET("banner/json")
    fun getBanner(): Observable<List<BannerEntity>>

    /**
     * 首页文章列表
     */
    @GET("article/list/{id}/json")
    fun getArticle(@Path("id") id: Int): Observable<AccountArticleEntity>

}