package com.gujun.common.base.ui.activity

import com.gujun.common.base.application.BaseApplication
import com.gujun.common.base.injection.component.DaggerActivityComponent
import com.gujun.common.base.injection.module.ActivityModule
import com.gujun.common.base.mvp.presenter.BasePresenter
import com.gujun.common.base.mvp.view.BaseView
import javax.inject.Inject

/**
 *    author : gujun
 *    date   : 2021/1/8 11:44
 *    desc   : MVP的基类Activity
 */
open abstract class BaseMvpActivity<T : BasePresenter> : BaseActivity(), BaseView {

    @Inject
    lateinit var mPresenter: T

    //此对象主要是能从appComponent中获取需要的数据，比如context:mActivityComponent.context()
    //还能将此对象传递给子Component，这样子Component就能获取ActivityModule对象提供的数据，但是子Component对应的Module得需要重写获取数据的方法
    protected lateinit var mActivityComponent: DaggerActivityComponent

    override fun initOperate() {
        initActivityInjection()
        injectComponent()
    }

    private fun initActivityInjection() {
        mActivityComponent = DaggerActivityComponent.builder()
            .activityModule(ActivityModule(this))
            .appComponent((application as BaseApplication).appComponent)
            .build() as DaggerActivityComponent
    }

    /**
     * 注入Presenter的关键
     */
    abstract fun injectComponent()
}