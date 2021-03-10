package com.gujun.common.base.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.gujun.common.R
import com.gujun.common.base.ui.activity.STATE_CONTENT
import com.gujun.common.base.ui.activity.STATE_EMPTY
import com.gujun.common.base.ui.activity.STATE_ERROR
import com.gujun.common.base.ui.activity.STATE_LOADING
import com.gujun.common.base.ui.widget.PageStateView
import com.gyf.immersionbar.ImmersionBar

/**
 *    author : gujun
 *    date   : 2021/1/6 14:41
 *    desc   : Fragment基类
 *    功能:设置沉浸式、设置页面stateView
 */
open abstract class BaseFragment : Fragment() {

    //页面状态视图
    private var stateView: PageStateView? = null

    //根视图
    private var rootView: View? = null

    //title视图
    private var titleView: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initOperate()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        rootView = getContentView(inflater)
        return rootView
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //stateView
        if (needShowStateView()) {
            addStateView(getStateParentView())
        }
        if (showDefaultToolbar()) {
            //初始化Toolbar
            initToolbar()
        }
        initView()
        initData()
        initListener()
        initOther()
    }

    /**
     * 这个是获取展示在系统默认content上的view
     */
    private fun getContentView(inflater: LayoutInflater): View {
        return if (showDefaultToolbar()) {
            getRealDefaultContentView(getPageContentView(inflater), inflater)
        } else {
            getPageContentView(inflater)
        }
    }

    /**
     * 这个是获取页面的view
     */
    open fun getPageContentView(inflater: LayoutInflater): View {
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
        rootView?.findViewById<ImageView>(R.id.commonBack)
            ?.setOnClickListener { requireActivity().onBackPressed() }
        titleView = rootView?.findViewById(R.id.commonTitleView)
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
    private fun addStateView(stateParentView: ViewGroup?) {
        if (stateParentView != null) {
            stateView =
                PageStateView.addStateView(stateParentView, ImmersionBar.getActionBarHeight(this))
            stateView?.setRetryClickListener { onRetryClick(it) }
        } else
            throw IllegalStateException("Fragment PageStateView parentView is null")
    }

    /**
     * 获取状态试图的父视图
     */
    open fun getStateParentView(): ViewGroup? = null

}