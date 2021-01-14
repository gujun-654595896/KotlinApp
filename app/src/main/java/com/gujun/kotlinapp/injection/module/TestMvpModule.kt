package com.gujun.kotlinapp.injection.module

import com.gujun.common.base.injection.scop.ComponentScope
import com.gujun.kotlinapp.mvp.TestMvpContract
import dagger.Module
import dagger.Provides

/**
 *    author : gujun
 *    date   : 2021/1/13 16:54
 *    desc   :
 */
@Module
class TestMvpModule(
    private val view: TestMvpContract.View,
    private val model: TestMvpContract.Model
) {

    @Provides
    @ComponentScope
    fun provideView(): TestMvpContract.View {
        return view
    }

    @Provides
    @ComponentScope
    fun provideModel(): TestMvpContract.Model {
        return model
    }

}