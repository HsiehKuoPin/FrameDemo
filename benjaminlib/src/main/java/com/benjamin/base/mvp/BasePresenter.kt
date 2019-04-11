package com.benjamin.base.mvp

import com.benjamin.http.RxManager


abstract class BasePresenter<V : IContract.IView, M : IContract.IModel> : IContract.IPresenter, RxManager() {

    var mModel = onCreateModel()
    var mView: V? = null
    abstract fun onCreateModel(): M

    override fun attachView(view: IContract.IView) {
        mView = view as V
    }

    override fun detachView() {
        mView = null
    }

    override fun unsubscribe() {
        dispose()
    }
}
