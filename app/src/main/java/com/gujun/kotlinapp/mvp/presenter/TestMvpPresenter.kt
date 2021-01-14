package com.gujun.kotlinapp.mvp.presenter

import com.gujun.kotlinapp.mvp.TestMvpContract
import com.gujun.kotlinapp.mvp.modelImpl.TestMvpModelImpl
import javax.inject.Inject

/**
 *    author : gujun
 *    date   : 2021/1/13 16:38
 *    desc   :
 */
class TestMvpPresenter : TestMvpContract.Presenter, TestMvpModelImpl.TestMvpModelImplCallBack {

    private var mView: TestMvpContract.View?
    private var mModel: TestMvpContract.Model?

    @Inject
    constructor(view: TestMvpContract.View, model: TestMvpContract.Model) {
        this.mView = view
        this.mModel = model
    }

    override fun getData() {
        mModel?.getData(this)
    }

    override fun onResult(data: String) {
        mView?.showData(data)
    }

}