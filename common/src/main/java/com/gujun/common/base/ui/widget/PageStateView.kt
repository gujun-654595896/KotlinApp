package com.gujun.common.base.ui.widget

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.annotation.LayoutRes
import com.gujun.common.R

/**
 *    author : gujun
 *    date   : 2021/1/14 14:05
 *    desc   : 页面状态视图
 */
class PageStateView constructor(
    context: Context, attrs: AttributeSet? = null,
    defStyleAttr: Int = 0, val stateViewPaddingTop: Int
) : FrameLayout(context, attrs, defStyleAttr) {

    private var loadingResourceId = R.layout.common_layout_state_loading
    private var emptyResourceId = R.layout.common_layout_state_empty
    private var errorResourceId = R.layout.common_layout_state_error

    private var loadingView: View? = null
    private var emptyView: View? = null
    private var errorView: View? = null

    private var currentState = 0

    private var stateViewRetryClickListener: ((v: View) -> Unit)? = null

    fun showLoadingView(): View? {
        currentState = STATE_LOADING
        if (loadingView == null)
            loadingView = inflateView(loadingResourceId)
        showView()
        return loadingView
    }

    fun showEmptyView(): View? {
        currentState = STATE_EMPTY
        if (emptyView == null)
            emptyView = inflateView(emptyResourceId)
        showView()
        return emptyView
    }

    fun showErrorView(retryBtnId: Int = 0): View? {
        currentState = STATE_ERROR
        if (errorView == null)
            errorView = inflateView(errorResourceId)
        //注意此处，如果是自定义的error需要传进来retryId,否则使用默认视图的，再找不到就无法传递重试事件
        errorView?.findViewById<View>(if (retryBtnId <= 0) R.id.retry else retryBtnId)
            ?.setOnClickListener {
                stateViewRetryClickListener?.invoke(it)
            }
        showView()
        return errorView
    }

    fun showContentView() {
        currentState = STATE_CONTENT
        visibility = View.GONE
    }

    private fun showView() {
        when (currentState) {
            STATE_LOADING -> {
                setViewVisible(loadingView, View.VISIBLE)
                setViewVisible(errorView, View.GONE)
                setViewVisible(emptyView, View.GONE)
            }
            STATE_EMPTY -> {
                setViewVisible(loadingView, View.GONE)
                setViewVisible(errorView, View.GONE)
                setViewVisible(emptyView, View.VISIBLE)
            }
            STATE_ERROR -> {
                setViewVisible(loadingView, View.GONE)
                setViewVisible(errorView, View.VISIBLE)
                setViewVisible(emptyView, View.GONE)
            }
            else -> {
                setViewVisible(loadingView, View.GONE)
                setViewVisible(errorView, View.GONE)
                setViewVisible(emptyView, View.GONE)
            }
        }
    }

    private fun setViewVisible(view: View?, visibility: Int) {
        if (visibility != view?.visibility)
            view?.visibility = visibility
    }

    private fun inflateView(@LayoutRes layoutResource: Int): View {
        val view = LayoutInflater.from(context).inflate(layoutResource, null, false)
        //拦截事件以防下传
        view.isClickable = true
        addView(view)
        return view
    }

    fun setLoadingResourceId(@LayoutRes resId: Int) {
        this.loadingResourceId = resId
    }

    fun setEmptyResourceId(@LayoutRes resId: Int) {
        this.emptyResourceId = resId
    }

    fun setErrorResourceId(@LayoutRes resId: Int) {
        this.errorResourceId = resId
    }

    fun setRetryClickListener(listener: (v: View) -> Unit) {
        this.stateViewRetryClickListener = listener
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        setPadding(0, stateViewPaddingTop, 0, 0)
    }

    companion object {

        const val STATE_LOADING = 1001
        const val STATE_ERROR = 1002
        const val STATE_EMPTY = 1003
        const val STATE_CONTENT = 1004

        fun addStateView(parentView: ViewGroup, stateViewPaddingTop: Int = 0): PageStateView {
            val stateView =
                PageStateView(parentView.context, stateViewPaddingTop = stateViewPaddingTop)
            parentView.addView(stateView)
            return stateView
        }
    }

}