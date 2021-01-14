package com.gujun.kotlinapp.mvp.modelImpl

import com.gujun.kotlinapp.mvp.TestMvpContract

/**
 *    author : gujun
 *    date   : 2021/1/14 10:19
 *    desc   :
 */
class TestMvpModelImpl : TestMvpContract.Model {
    override fun getData(callback: TestMvpModelImplCallBack) {
        callback?.onResult("test")
    }

    interface TestMvpModelImplCallBack {
        fun onResult(data: String)
    }
}