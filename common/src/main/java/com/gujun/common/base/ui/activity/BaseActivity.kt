package com.gujun.common.base.ui.activity

import android.os.Bundle
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import com.gujun.common.base.ui.widget.PageStateView
import com.gyf.immersionbar.ImmersionBar

/**
 *    author : gujun
 *    date   : 2021/1/6 14:38
 *    desc   : Activity基类
 *    功能:设置沉浸式、设置页面stateView
 */
open abstract class BaseActivity : AppCompatActivity() {

    //沉浸式状态栏的字体是否是深色，默认浅色
    private var immersionBarDarkFont: Boolean = false

    //沉浸式状态栏后页面是否添加padding
    private var immersionBarPadding: Boolean = true

    val STATE_LOADING = 1001
    val STATE_ERROR = 1002
    val STATE_EMPTY = 1003
    val STATE_CONTENT = 1004

    //页面状态视图
    private var stateView: PageStateView? = null

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
        //stateView
        if (needShowStateView()) {
            addStateView()
        }
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

    /**
     * 展示对应的状态视图
     */
    open fun showStateView(state: Int) {
        when (state) {
            STATE_LOADING -> stateView?.showLoadingView()
            STATE_EMPTY -> stateView?.showEmptyView()
            STATE_ERROR -> stateView?.showErrorView()
            STATE_CONTENT -> stateView?.showContentView()
        }
    }

    /**
     * 是否展示StateView
     */
    open fun needShowStateView() = false

    /**
     * 设置状态视图的布局，在initView()中设置即可,但是得在调用showStateView(state: Int)前
     */
    fun setStateViewResourceId(loadingResId: Int, errorResId: Int, emptyResId: Int) {
        stateView?.setEmptyResourceId(emptyResId)
        stateView?.setLoadingResourceId(loadingResId)
        stateView?.setErrorResourceId(errorResId)
    }

    /**
     * 添加状态视图
     */
    private fun addStateView() {
        val rootView = this.window.decorView.findViewById(android.R.id.content) as ViewGroup
        stateView = PageStateView.addStateView(rootView)
    }

}