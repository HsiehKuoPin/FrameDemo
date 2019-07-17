package com.onechat.cat.ui.home

import com.benjamin.base.mvp.IContract.*
import com.onechat.cat.entity.AccountArticleEntity
import com.onechat.cat.entity.BannerEntity
import com.onechat.cat.entity.MultipleItem
import io.reactivex.Observable

/**
 * @describe
 * @author  Benjamin
 * @date 2019/5/28  11:42
 * 								 - generate by MvpAutoCodePlus plugin.
 */

interface IHomeContract {
    interface View : IView {
        fun getHomes(datas: List<MultipleItem>)

    }

    interface Presenter : IPresenter {
        fun getHomes()
    }

    interface Model : IModel {
        //        fun getAccounts(): Observable<List<AccountEntity>>
        fun getBanner(): Observable<List<BannerEntity>>

        fun getArticle(id: Int): Observable<AccountArticleEntity>

    }
}
