package com.gujun.common.base.injection.module

import android.app.Activity
import com.gujun.common.base.injection.scop.ActivityScope
import dagger.Module
import dagger.Provides

/**
 *    author : gujun
 *    date   : 2021/1/11 16:31
 *    desc   : 此处的作用域一定要和Component中对应
 */
@Module
class ActivityModule(private val activity: Activity) {

    @ActivityScope
    @Provides
    fun provideActivity(): Activity {
        return this.activity
    }

}