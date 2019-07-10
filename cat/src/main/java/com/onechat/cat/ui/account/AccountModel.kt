package com.onechat.cat.ui.account

import com.onechat.cat.entity.AccountEntity
import com.onechat.cat.net.NetProvider
import com.onechat.cat.ui.account.IAccountContract
import io.reactivex.Observable

/**
 * @describe
 * @author  Benjamin
 * @date 2019/7/9  17:39
 * 								 - generate by MvpAutoCodePlus plugin.
 */

class AccountModel : IAccountContract.Model {

    override fun getAccounts(): Observable<List<AccountEntity>> {
        return NetProvider.getInstance().getAccounts()
    }
}

