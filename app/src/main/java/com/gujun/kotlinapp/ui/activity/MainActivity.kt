package com.gujun.kotlinapp.ui.activity

import android.content.Intent
import com.gujun.common.base.ui.activity.BaseActivity
import com.gujun.database.entity.TestDataEntity
import com.gujun.database.utils.TestDataEntityUtil
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
        updateUi()
    }

    override fun initData() {

    }

    override fun initListener() {
        jump.setOnClickListener { startActivity(Intent(this, SecondActivity::class.java)) }
        database.setOnClickListener {
            addDataToDb()
            updateUi()
        }
    }

    private fun addDataToDb() {
        val data = TestDataEntity()
        data.id_ = 1
        data.name = "我是数据库测试数据"
        TestDataEntityUtil.getInstance().add(data)
    }

    private fun updateUi() {
        val list = TestDataEntityUtil.getInstance().queryAll()
        val builder = StringBuilder()
        list.forEach { builder.append(it.name).append(",,") }
        content.text = builder
    }
}