package com.gujun.common.base.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.gujun.common.base.ui.widget.PageStateView

/**
 *    author : gujun
 *    date   : 2021/1/6 14:41
 *    desc   : Fragment基类
 *    功能:设置沉浸式、设置页面stateView
 */
open abstract class BaseFragment : Fragment() {

    val STATE_LOADING = 1001
    val STATE_ERROR = 1002
    val STATE_EMPTY = 1003
    val STATE_CONTENT = 1004

    //页面状态视图
    private var stateView: PageStateView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initOperate()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(getLayoutId(), container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //stateView
        if (needShowStateView()) {
            addStateView(getStateParentView())
        }
        initView()
        initData()
        initListener()
    }

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
    private fun addStateView(stateParentView: ViewGroup?) {
        if (stateParentView != null) {
            stateView = PageStateView.addStateView(stateParentView)
        } else
            throw IllegalStateException("Fragment PageStateView parentView is null")
    }

    /**
     * 获取状态试图的父视图
     */
    open fun getStateParentView(): ViewGroup? = null

}