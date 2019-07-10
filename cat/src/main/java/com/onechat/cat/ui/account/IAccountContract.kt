package com.onechat.cat.ui.account

import com.benjamin.base.mvp.IContract.*
import com.onechat.cat.entity.AccountEntity
import io.reactivex.Observable

/**
 * @describe
 * @author  Benjamin
 * @date 2019/7/9  17:39
 * 								 - generate by MvpAutoCodePlus plugin.
 */

interface IAccountContract {
    interface View : IView {
        fun getAccountsSuccess(accounts: List<AccountEntity>)
    }

    interface Presenter : IPresenter {
        fun getAccounts()
    }

    interface Model : IModel {
        fun getAccounts(): Observable<List<AccountEntity>>
    }
}
