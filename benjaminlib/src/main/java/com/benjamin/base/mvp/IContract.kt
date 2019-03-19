package com.benjamin.base.mvp

/**
 * @author  Ben
 * @date 2019/3/5
 */
interface IContract {
    interface IPresenter {
        fun onDestroy()
    }

    interface IView {
        fun onTokenInvalid()
    }

    interface IModel {
    }

}