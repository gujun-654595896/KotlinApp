package com.gujun.kotlinapp.mvp

import com.gujun.common.base.mvp.presenter.BasePresenter
import com.gujun.common.base.mvp.view.BaseView
import com.gujun.kotlinapp.mvp.modelImpl.TestMvpModelImpl

/**
 *    author : gujun
 *    date   : 2021/1/13 16:31
 *    desc   :
 */
interface TestMvpContract {

    interface View : BaseView {

        fun showData(data: String)

    }

    interface Presenter : BasePresenter {
        fun getData()
    }

    interface Model {
        fun getData(callback: TestMvpModelImpl.TestMvpModelImplCallBack)
    }
}