package com.onechat.cat.ui.home

import com.onechat.cat.entity.AccountEntity
import com.onechat.cat.net.NetProvider
import io.reactivex.Observable

/**
 * @describe
 * @author  Benjamin
 * @date 2019/5/28  11:42
 * 								 - generate by MvpAutoCodePlus plugin.
 */

class HomeModel : IHomeContract.Model {
    override fun getAccounts(): Observable<List<AccountEntity>> {
        return NetProvider.instance.getAccounts()
    }

}

