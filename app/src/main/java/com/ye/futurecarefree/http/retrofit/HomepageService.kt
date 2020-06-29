package com.ye.futurecarefree.http.retrofit

import com.ye.futurecarefree.bean.HomepageTitleBannerEntity
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * Create Time : 2020-06-23
 * Author : WoDong
 * Desc : 主页网络请求api
 */
interface HomepageService {
    @GET("FutureCarefree/{path}")
    fun getTitleBanner(@Path("path") path:String):Observable<HomepageTitleBannerEntity>

}