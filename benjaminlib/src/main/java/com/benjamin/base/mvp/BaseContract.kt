package com.benjamin.base.mvp

/**
 * @author  Ben
 * @date 2019/3/5
 */
interface BaseContract {
    interface BasePresenter {
        fun onDestroy()
    }

    interface BaseView {
        fun onTokenInvalid()
    }

}