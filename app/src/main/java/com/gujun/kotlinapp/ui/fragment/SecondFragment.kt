package com.gujun.kotlinapp.ui.fragment

import android.os.Bundle
import com.gujun.common.base.ui.fragment.BaseFragment
import com.gujun.kotlinapp.R

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

    companion object {
        fun newInstance(): SecondFragment {
            val fragment = SecondFragment()
            var bundle = Bundle()
            fragment.arguments = bundle
            return fragment
        }
    }
}