package com.gujun.common.base.ui.fragment

import com.gujun.common.base.mvp.presenter.BasePresenter
import com.gujun.common.base.mvp.view.BaseView

/**
 *    author : gujun
 *    date   : 2021/1/8 11:44
 *    desc   : MVP的基类Fragment
 */
open abstract class BaseMvpFragment<T : BasePresenter> : BaseFragment(), BaseView {

}