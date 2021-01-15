package com.gujun.network

import com.gujun.network.interceptor.HeaderInterceptor
import com.gujun.network.interceptor.LoginInterceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 *    author : gujun
 *    date   : 2020/10/21 14:19
 *    desc   : retrofit请求工具类
 */
class Requester {

    companion object {
        private fun <T> getService(baseUrl: String, service: Class<T>): T {
            var client = OkHttpClient.Builder()
                .connectTimeout(10, TimeUnit.SECONDS)//设置连接超时
                .readTimeout(10, TimeUnit.SECONDS)//设置读取超时
                .addInterceptor(HeaderInterceptor())//添加拦截器
                .addInterceptor(LoginInterceptor())//添加拦截器
                .build()

            val retrofit = Retrofit.Builder().baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())//需要引入retrofit的converter-gson库

                //2、****** 2.6.0以后去除之前添加的 kotlin-coroutines-adapter，因为我们不再需要人工返回 Deferred<T> 对象 ******
                //.addCallAdapterFactory(CoroutineCallAdapterFactory.invoke())//正常的retrofit返回的是call，此方法用于将call转化成Rxjava的Observable或协程的Deferred对象等
                .client(client)
                .build()
            return retrofit.create(service)
        }

        fun apiService(): ApiService {
            return getService(ApiService.baseUrl, ApiService::class.java)
        }
    }

}