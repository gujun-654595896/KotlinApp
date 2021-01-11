package com.gujun.kotlinapp.app

import com.alibaba.android.arouter.launcher.ARouter
import com.gujun.common.base.application.BaseApplication
import com.gujun.kotlinapp.BuildConfig

/**
 *    author : gujun
 *    date   : 2020/11/6 10:28
 *    desc   :
 */
open class KotlinApplication : BaseApplication() {

    override fun onCreate() {
        super.onCreate()
        initArouter()
    }

    /**
     * 初始化ARouter
     */
    private fun initArouter() {
        if (BuildConfig.IS_DEBUG) { // 这两行必须写在init之前，否则这些配置在init过程中将无效
            ARouter.openLog();      // 打印日志
            ARouter.openDebug();    // 开启调试模式(如果在InstantRun模式下运行，必须开启调试模式！线上版本需要关闭,否则有安全风险)
        }
        ARouter.init(this) // 尽可能早，推荐在Application中初始化
    }
}