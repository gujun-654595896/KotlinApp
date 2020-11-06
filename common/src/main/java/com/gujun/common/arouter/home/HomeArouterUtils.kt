package com.gujun.common.arouter.home

import android.app.Activity
import com.alibaba.android.arouter.launcher.ARouter

/**
 *    author : gujun
 *    date   : 2020/11/6 10:08
 *    desc   : Home Module 的arouter跳转工具
 */

fun jumpToHomeActivity(activity: Activity) {

    ARouter.getInstance().build("/home/HomeActivity").navigation(activity)

}