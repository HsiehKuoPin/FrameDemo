package com.onechat.cat.ui.account

import com.benjamin.base.mvp.BasePresenter
import com.benjamin.utils.extension.io2main
import com.benjamin.utils.extension.subscribeByHandle

/**
 * @describe
 * @author  Benjamin
 * @date 2019/7/9  17:39
 * 								 - generate by MvpAutoCodePlus plugin.
 */

class AccountPresenter : BasePresenter<IAccountContract.View, IAccountContract.Model>(), IAccountContract.Presenter {

    override fun onCreateModel(): IAccountContract.Model {
        return AccountModel()
    }

    override fun getAccounts() {
        mModel.getAccounts()
                .io2main()
                .subscribeByHandle(mView) {
                    mView?.getAccountsSuccess(it)
                }
    }

}

