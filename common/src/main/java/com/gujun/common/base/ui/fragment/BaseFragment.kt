package com.gujun.common.base.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

/**
 *    author : gujun
 *    date   : 2021/1/6 14:41
 *    desc   : Fragment基类
 */
open abstract class BaseFragment : Fragment() {

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
}