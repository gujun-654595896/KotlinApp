package com.gujun.common.base.injection.component

import android.app.Activity
import android.content.Context
import com.gujun.common.base.injection.module.ActivityModule
import com.gujun.common.base.injection.scop.ActivityScope
import dagger.Component

/**
 *    author : gujun
 *    date   : 2021/1/11 16:21
 *    desc   : Activity级别的组件依赖App级别的组件,作用域Scope一定和Module中的保持一致
 */
@ActivityScope
@Component(dependencies = [(AppComponent::class)], modules = [(ActivityModule::class)])
interface ActivityComponent {

    //此处的context是AppComponent中定义 并且 在AppModule中@Provides的
    //在这个对象（DaggerActivityComponent）中能调用到,
    //所以如果子Component(ActivityComponent)想获取父Component(AppComponent)中定义的数据，就需要在子Component中添加对应的方法
    //首相得确保父Component@Provides了此数据
    fun context(): Context

    fun activity(): Activity
}