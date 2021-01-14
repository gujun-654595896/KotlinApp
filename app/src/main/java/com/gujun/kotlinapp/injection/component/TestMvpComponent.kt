package com.gujun.kotlinapp.injection.component

import android.app.Activity
import com.gujun.common.base.injection.component.ActivityComponent
import com.gujun.common.base.injection.scop.ComponentScope
import com.gujun.kotlinapp.injection.module.TestMvpModule
import com.gujun.kotlinapp.ui.activity.TestMvpActivity
import dagger.Component

/**
 *    author : gujun
 *    date   : 2021/1/13 16:53
 *    desc   :
 */
@ComponentScope
@Component(dependencies = [(ActivityComponent::class)], modules = [(TestMvpModule::class)])
interface TestMvpComponent {
    fun inject(activity: TestMvpActivity)

    //重写ActivityComponent的此方法，DaggerTestMvpComponent对能就能获取ActivityComponent对应的值
    fun activity(): Activity
}