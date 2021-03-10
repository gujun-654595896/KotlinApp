package com.gujun.common.base.ui.activity

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.gujun.common.R
import com.gujun.common.base.ui.widget.PageStateView
import com.gyf.immersionbar.ImmersionBar

/**
 *    author : gujun
 *    date   : 2021/1/6 14:38
 *    desc   : Activity基类
 *    功能:设置沉浸式、设置页面stateView
 */

const val STATE_LOADING = 1001
const val STATE_ERROR = 1002
const val STATE_EMPTY = 1003
const val STATE_CONTENT = 1004

open abstract class BaseActivity : AppCompatActivity() {

    //沉浸式状态栏的字体是否是深色，默认浅色
    private var immersionBarDarkFont: Boolean = false

    //沉浸式状态栏后页面是否添加padding
    private var immersionBarPadding: Boolean = true

    //页面状态视图
    private var stateView: PageStateView? = null

    //title视图
    private var titleView: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //设置布局
        setContentView(getContentView())
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
        if (showDefaultToolbar()) {
            //初始化Toolbar
            initToolbar()
        }
        //初始化视图
        initView()
        //初始化数据
        initData()
        //初始化监听
        initListener()
        //初始化其他
        initOther()
    }

    /**
     * 这个是获取展示在系统默认content上的view
     */
    private fun getContentView(): View {
        val inflater = LayoutInflater.from(this)
        return if (showDefaultToolbar()) {
            getRealDefaultContentView(getPageContentView(inflater), inflater)
        } else {
            getPageContentView(inflater)
        }
    }

    /**
     * 这个是获取页面的view
     */
    open fun getPageContentView(inflater: LayoutInflater = LayoutInflater.from(this)): View {
        return inflater.inflate(getLayoutId(), null)
    }

    /**
     * 获取页面视图的容器
     */
    private fun getRealDefaultContentView(contentView: View, inflater: LayoutInflater): View {
        //添加了Toolbar后的Content容器
        val realContentView: ViewGroup =
            inflater.inflate(R.layout.common_layout_toolbar_content, null) as ViewGroup
        //布局参数
        val vl = ViewGroup.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.MATCH_PARENT
        )
        //添加页面视图
        realContentView.addView(contentView, vl)
        return realContentView
    }

    private fun initToolbar() {
        findViewById<ImageView>(R.id.commonBack).setOnClickListener { onBackPressed() }
        titleView = findViewById(R.id.commonTitleView)
    }

    fun setTitle(title: String) {
        titleView?.text = title
    }

    /**
     * 是否展示默认的ToolBar,默认不展示Toolbar
     */
    open fun showDefaultToolbar(): Boolean = false

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
     * 初始化其他的数据，在以上所有设置完之后
     */
    open fun initOther() {}

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
     * 状态视图的重试按钮点击
     */
    open fun onRetryClick(v: View) {

    }

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
        stateView = PageStateView.addStateView(rootView, ImmersionBar.getActionBarHeight(this))
        stateView?.setRetryClickListener { onRetryClick(it) }
    }

}