package com.gujun.common.arouter.mine

import android.app.Activity
import com.alibaba.android.arouter.launcher.ARouter

/**
 *    author : gujun
 *    date   : 2020/11/6 10:08
 *    desc   : Mine Module 的arouter跳转工具
 */

fun jumpToMineActivity(activity: Activity) {

    ARouter.getInstance().build("/mine/MineActivity").navigation(activity)

}