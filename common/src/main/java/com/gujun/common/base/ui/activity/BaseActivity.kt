package com.gujun.common.base.ui.activity

import android.os.Bundle
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import com.gyf.immersionbar.ImmersionBar

/**
 *    author : gujun
 *    date   : 2021/1/6 14:38
 *    desc   : Activity基类
 */
open abstract class BaseActivity : AppCompatActivity() {

    //沉浸式状态栏的字体是否是深色，默认浅色
    private var immersionBarDarkFont: Boolean = false

    //沉浸式状态栏后页面是否添加padding
    private var immersionBarPadding: Boolean = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //设置布局
        setContentView(getLayoutId())
        //沉浸式状态栏
        if (isSetImmersionBar()) {
            setImmersionBar()
        }
        //一些需要在onCreate中初始化的操作
        initOperate()
        //初始化视图
        initView()
        //初始化数据
        initData()
        //初始化监听
        initListener()
    }

    /**
     * 设置页面布局
     */
    abstract fun getLayoutId(): Int

    /**
     * 是否设置沉浸式状态栏
     */
    open fun isSetImmersionBar() = true

    /**
     * 设置沉浸式状态栏字体颜色是深色还是浅色
     * isSetImmersionBar 继承返回前就需要调用此方法
     */
    fun setImmersionBarDarkFont(immersionBarDarkFont: Boolean) {
        this.immersionBarDarkFont = immersionBarDarkFont
    }

    /**
     * 设置沉浸式状态栏是否需要设置状态栏高度的padding
     * isSetImmersionBar 继承返回前就需要调用此方法
     */
    fun setImmersionBarPadding(immersionBarPadding: Boolean) {
        this.immersionBarPadding = immersionBarPadding
    }

    /**
     * 设置沉浸式状态栏配置
     */
    private fun setImmersionBar() {
        ImmersionBar.with(this)
            .statusBarDarkFont(immersionBarDarkFont)
            .init()

        if (immersionBarPadding) {
            val rootView = this.window.decorView.findViewById(android.R.id.content) as ViewGroup
            rootView.setPadding(
                0,
                ImmersionBar.getStatusBarHeight(this),
                0,
                0
            )
        }
    }

    /**
     * 需要在onCreate中初始化的操作
     */
    open fun initOperate() {}

    /**
     * 初始化视图
     */
    abstract fun initView()

    /**
     * 初始化数据
     */
    abstract fun initData()

    /**
     * 初始化监听
     */
    open fun initListener() {}

}