package com.benjamin.base.mvp

import android.os.Bundle
import com.benjamin.base.BaseActivity
import com.benjamin.base.mvp.IContract.IPresenter
import com.benjamin.base.mvp.IContract.IView

/**
 * @author  Ben
 * @date 2019/1/17
 */
abstract class MvpActivity<P : IPresenter> : BaseActivity(), IView {
    protected lateinit var mPresenter: P

    override fun onCreate(savedInstanceState: Bundle?) {
        mPresenter = onCreatePresenter()
        mPresenter.attachView(this)
        super.onCreate(savedInstanceState)
    }

    abstract fun onCreatePresenter(): P

    override fun onTokenInvalid() {
    }

    override fun onDestroy() {
        mPresenter.unsubscribe()
        mPresenter.detachView()
        super.onDestroy()
    }

    override fun showProgressView() {
        loadingView.showProgressView()
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