package com.gujun.kotlinapp.ui.activity

import com.gujun.common.base.ui.activity.BaseActivity
import com.gujun.kotlinapp.R
import com.gujun.kotlinapp.ui.fragment.SecondFragment

class SecondActivity : BaseActivity() {

    override fun getLayoutId(): Int {
        return R.layout.app_activity_second
    }

    override fun isSetImmersionBar(): Boolean {
        setImmersionBarDarkFont(true)
        setImmersionBarPadding(true)
        return true
    }

    override fun initView() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.container, SecondFragment.newInstance()).commit()
    }

    override fun initData() {

    }
}