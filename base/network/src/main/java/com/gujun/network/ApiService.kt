package com.gujun.network

import com.gujun.network.model.Data
import retrofit2.http.GET
import retrofit2.http.QueryMap

/**
 *    author : gujun
 *    date   : 2020/10/21 14:15
 *    desc   : 请求接口,网上找的公开API接口：https://api.nowtime.cc/v1/ipv4?ip=223.5.5.5
 */
interface ApiService {

    companion object {
        const val baseUrl = "https://api.nowtime.cc/"
    }

    //1、******  2.6.0以后这里返回的是真实对象，注意此函数前加suspend修饰  ******
    @GET("v1/ipv4")
    suspend fun getDataAsync(@QueryMap map: Map<String, String>): Data

}