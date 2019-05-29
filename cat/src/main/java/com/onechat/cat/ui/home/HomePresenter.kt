package com.onechat.cat.ui.home

import com.benjamin.base.mvp.BasePresenter
import com.benjamin.utils.extension.io2main
import com.benjamin.utils.extension.subscribeByHandle

/**
 * @describe
 * @author  Benjamin
 * @date 2019/5/28  11:42
 * 								 - generate by MvpAutoCodePlus plugin.
 */

class HomePresenter : BasePresenter<IHomeContract.View, IHomeContract.Model>(), IHomeContract.Presenter {

    override fun onCreateModel(): IHomeContract.Model {
        return HomeModel()
    }

    override fun getAccounts() {
        mModel.getAccounts()
            .io2main()
            .subscribeByHandle(
                onSuccess = {
                    mView?.getAccountsSuccess(it)
                },
                onFailure = {
                    mView?.getAccountsFail(it.toString())
                }
            )
    }

}

