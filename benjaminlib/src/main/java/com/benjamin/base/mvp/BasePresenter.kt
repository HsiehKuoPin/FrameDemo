package com.benjamin.base.mvp

import com.benjamin.http.RxManager


abstract class BasePresenter<V : IContract.IView, M : IContract.IModel>(var mView: V?) : IContract.IPresenter, RxManager() {

    var mModel = onCreateModel()


    abstract fun onCreateModel(): M


    override fun onDestroy() {
        dispose()
        mView?.let { mView = null }
    }
}
