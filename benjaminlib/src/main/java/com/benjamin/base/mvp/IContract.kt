package com.benjamin.base.mvp

/**
 * @author  Ben
 * @date 2019/3/5
 */
interface IContract {
    interface IPresenter {
        fun attachView(view: IView)
        fun detachView()
        fun unsubscribe()
    }

    interface IView {
        fun onTokenInvalid()

        fun showProgressView()

        fun showErrorView(errorMsg: String)

        fun showNetworkErrorView(errorMsg: String)

        fun showEmptyView()

        fun showContentView()
    }

    interface IModel {
    }

}