package com.gujun.kotlinapp.ui.fragment

import android.app.Activity
import android.os.Bundle
import com.gujun.common.arouter.home.jumpToHomeActivity
import com.gujun.common.base.ui.fragment.BaseFragment
import com.gujun.kotlinapp.R
import kotlinx.android.synthetic.main.fragment_second.*

/**
 *    author : gujun
 *    date   : 2021/1/7 15:02
 *    desc   :
 */
class SecondFragment : BaseFragment() {

    override fun getLayoutId(): Int {
        return R.layout.fragment_second
    }

    override fun initView() {

    }

    override fun initData() {

    }

    override fun initListener() {
        jump.setOnClickListener { jumpToHomeActivity(activity as Activity) }

    }

    companion object {
        fun newInstance(): SecondFragment {
            val fragment = SecondFragment()
            var bundle = Bundle()
            fragment.arguments = bundle
            return fragment
        }
    }
}