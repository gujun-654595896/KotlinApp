package com.gujun.network.interceptor

import android.util.Log
import okhttp3.Interceptor
import okhttp3.Response

/**
 *    author : gujun
 *    date   : 2020/10/21 14:31
 *    desc   : 接口请求拦截器
 */
class LoginInterceptor : Interceptor {

    val TAG = "Retrofit"

    override fun intercept(chain: Interceptor.Chain): Response {
        var request = chain.request()
        //输出请求的信息
        Log.e(TAG, "请求数据：${request.url()},\n,${request.body()}")
        //执行请求
        val response = chain.proceed(request)
        //结果信息
        Log.e(TAG, "结果数据：${response.isSuccessful},\n,${response.peekBody(1024)?.string()}")

        return response

    }
}