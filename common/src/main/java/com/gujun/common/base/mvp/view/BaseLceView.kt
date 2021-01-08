package com.gujun.common.base.mvp.view

/**
 *    author : gujun
 *    date   : 2021/1/8 13:55
 *    desc   : MVP的view
 */
interface BaseLceView {
    /**
     * 展示加载中视图
     */
    fun showLoading()

    /**
     * 展示数据
     */
    fun showContent()

    /**
     * 展示错误
     */
    fun showError()

    /**
     * 展示空视图
     */
    fun showEmpty()
}