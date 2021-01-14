package com.gujun.common.base.injection.component

import android.content.Context
import com.gujun.common.base.injection.module.AppModule
import com.gujun.common.base.injection.scop.AppScope
import dagger.Component

/**
 *    author : gujun
 *    date   : 2021/1/11 16:22
 *    desc   : App级别组件,作用域Scope一定和Module中的保持一致
 */
@AppScope
@Component(modules = [(AppModule::class)])
interface AppComponent {

    fun context(): Context
}