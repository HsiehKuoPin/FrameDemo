package com.onechat.cat.ui.home

import com.benjamin.base.mvp.IContract.*
import com.onechat.cat.entity.AccountEntity
import io.reactivex.Observable

/**
 * @describe
 * @author  Benjamin
 * @date 2019/5/28  11:42
 * 								 - generate by MvpAutoCodePlus plugin.
 */

interface IHomeContract {
    interface View : IView {
        fun getAccountsSuccess(accounts: List<AccountEntity>)
        fun getAccountsFail(msg: String)
    }

    interface Presenter : IPresenter {
        fun getAccounts()
    }

    interface Model : IModel {
        fun getAccounts(): Observable<List<AccountEntity>>
    }
}
