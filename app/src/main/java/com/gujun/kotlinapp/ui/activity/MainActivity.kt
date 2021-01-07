package com.gujun.kotlinapp.ui.activity

import android.content.Intent
import com.gujun.common.base.ui.activity.BaseActivity
import com.gujun.kotlinapp.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {

    override fun getLayoutId(): Int {
        return R.layout.activity_main
    }

    override fun isSetImmersionBar(): Boolean {
        setImmersionBarDarkFont(true)
        setImmersionBarPadding(true)
        return true
    }

    override fun initView() {

    }

    override fun initData() {

    }

    override fun initListener() {
        jump.setOnClickListener { startActivity(Intent(this, SecondActivity::class.java)) }
    }
}