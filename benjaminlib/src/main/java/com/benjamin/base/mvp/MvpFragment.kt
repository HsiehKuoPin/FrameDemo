package com.benjamin.base.mvp

import android.os.Bundle
import android.view.View
import com.benjamin.base.BaseFragment
import com.benjamin.base.mvp.IContract.IPresenter
import com.benjamin.base.mvp.IContract.IView

/**
 * @author  Ben
 * @date 2019/1/17
 */
abstract class MvpFragment<P : IPresenter> : BaseFragment(),IView {
    protected lateinit var mPresenter: P

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        mPresenter = onCreatePresenter()
        mPresenter.attachView(this)
        super.onViewCreated(view, savedInstanceState)
    }

    abstract fun onCreatePresenter(): P

    override fun onTokenInvalid() {
    }

    override fun onDestroy() {
        mPresenter.unsubscribe()
        super.onDestroy()
    }

    override fun showProgressView() {
        if (loadingView.isProgressShowing.not()) loadingView.showProgressView()
    }

    override fun showErrorView(errorMsg: String) {
        loadingView.showErrorView(errorMsg)
    }

    override fun showNetworkErrorView(errorMsg: String) {
        loadingView.showNetworkErrorView(errorMsg)
    }

    override fun showEmptyView() {
        loadingView.showEmptyView()
    }

    override fun showContentView() {
        loadingView.showContentView()
    }

}