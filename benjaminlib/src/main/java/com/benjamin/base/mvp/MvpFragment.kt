package com.benjamin.base.mvp

import com.benjamin.base.BaseFragment
import com.benjamin.base.mvp.BaseContract.BasePresenter
import com.benjamin.base.mvp.BaseContract.BaseView

/**
 * @author  Ben
 * @date 2019/1/17
 */
abstract class MvpFragment<P : BasePresenter> : BaseFragment(),BaseView {
    protected var mPresenter: P? = null

    override fun initData() {
        super.initData()
        mPresenter = getPresenter()
    }

    protected open fun getPresenter(): P? {
        return null
    }

    override fun onTokenInvalid() {
    }

}