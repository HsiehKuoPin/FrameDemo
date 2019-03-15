package com.benjamin.base.mvp

import com.benjamin.http.RxManager


open class BasePresenter<V : BaseContract.BaseView>(var mView: V?) : BaseContract.BasePresenter, RxManager() {

    override fun onDestroy() {
        dispose()
        mView?.let { mView = null }
    }
}
