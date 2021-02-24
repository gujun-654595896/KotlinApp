package com.gujun.kotlinapp.ui.activity

import android.util.Log
import com.gujun.common.arouter.home.jumpToHomeActivity
import com.gujun.common.base.ui.activity.BaseMvpActivity
import com.gujun.kotlinapp.R
import com.gujun.kotlinapp.injection.component.DaggerTestMvpComponent
import com.gujun.kotlinapp.injection.module.TestMvpModule
import com.gujun.kotlinapp.mvp.TestMvpContract
import com.gujun.kotlinapp.mvp.presenter.TestMvpPresenter
import kotlinx.android.synthetic.main.app_activity_test_mvp.*

/**
 *    author : gujun
 *    date   : 2021/1/13 16:22
 *    desc   : 测试dagger2+MVP框架
 */
class TestMvpActivity : BaseMvpActivity<TestMvpPresenter>(), TestMvpContract.View {

    override fun injectComponent() {
        DaggerTestMvpComponent.builder()
            .testMvpModule(TestMvpModule(this))
            .activityComponent(mActivityComponent)
            .build().inject(this)
    }

    override fun isSetImmersionBar(): Boolean {
        setImmersionBarDarkFont(true)
        setImmersionBarPadding(true)
        return true
    }

    override fun getLayoutId(): Int {
        return R.layout.app_activity_test_mvp
    }

    override fun initView() {
    }

    override fun initData() {
        mPresenter.getData()
    }

    override fun initListener() {
        jump.setOnClickListener { jumpToHomeActivity(this) }
    }

    override fun showData(data: String) {
        Log.e("testData", "showData: $data")
    }
}