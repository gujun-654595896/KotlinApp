package com.gujun.common.base.ui.activity

import com.gujun.common.base.mvp.presenter.BasePresenter
import com.gujun.common.base.mvp.view.BaseView

/**
 *    author : gujun
 *    date   : 2021/1/8 11:44
 *    desc   : MVP的基类Activity
 */
open abstract class BaseMvpActivity<T : BasePresenter> : BaseActivity(), BaseView {

}