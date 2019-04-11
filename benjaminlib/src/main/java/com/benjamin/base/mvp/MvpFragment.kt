package com.benjamin.base.mvp

import com.benjamin.base.BaseFragment
import com.benjamin.base.mvp.IContract.IPresenter
import com.benjamin.base.mvp.IContract.IView

/**
 * @author  Ben
 * @date 2019/1/17
 */
abstract class MvpFragment<P : IPresenter> : BaseFragment(),IView {
    protected var mPresenter = getPresenter()

    override fun initData() {
        super.initData()
        mPresenter = getPresenter()
    }

    abstract fun getPresenter(): P

    override fun onTokenInvalid() {
    }

    override fun onDestroy() {
        mPresenter.unsubscribe()
        super.onDestroy()
    }

}