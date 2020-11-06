package com.gujun.common.arouter.media

import android.app.Activity
import com.alibaba.android.arouter.launcher.ARouter

/**
 *    author : gujun
 *    date   : 2020/11/6 10:08
 *    desc   : Media Module 的arouter跳转工具
 */

fun jumpToMediaActivity(activity: Activity) {

    ARouter.getInstance().build("/media/MediaActivity").navigation(activity)

}