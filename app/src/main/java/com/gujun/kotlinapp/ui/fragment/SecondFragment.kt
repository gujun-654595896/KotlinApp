package com.gujun.kotlinapp.ui.fragment

import android.content.Intent
import android.os.Bundle
import com.gujun.common.base.ui.fragment.BaseFragment
import com.gujun.kotlinapp.R
import com.gujun.kotlinapp.ui.activity.TestMvpActivity
import kotlinx.android.synthetic.main.app_fragment_second.*

/**
 *    author : gujun
 *    date   : 2021/1/7 15:02
 *    desc   :
 */
class SecondFragment : BaseFragment() {

    override fun getLayoutId(): Int {
        return R.layout.app_fragment_second
    }

    override fun initView() {

    }

    override fun initData() {

    }

    override fun initListener() {
        jump.setOnClickListener {
            activity!!.startActivity(
                Intent(
                    activity,
                    TestMvpActivity::class.java
                )
            )
        }

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