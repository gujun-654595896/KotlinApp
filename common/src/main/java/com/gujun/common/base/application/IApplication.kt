package com.gujun.common.base.application

import android.app.Application

/**
 *    author : gujun
 *    date   : 2021/1/11 11:38
 *    desc   : 各个子Module初始化的接口
 */
interface IApplication {

    fun init(application: Application?)
}