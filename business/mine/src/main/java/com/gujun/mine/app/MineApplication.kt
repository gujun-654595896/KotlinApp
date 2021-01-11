package com.gujun.mine.app

import android.app.Application
import com.gujun.common.base.application.IApplication

/**
 *    author : gujun
 *    date   : 2021/1/11 16:16
 *    desc   : 相关的初始化操作在此页面,
 * 此页面的调用不是直接调用的，而是通过字节码插桩调用的
 */
class MineApplication : IApplication {
    override fun init(application: Application?) {
    }
}