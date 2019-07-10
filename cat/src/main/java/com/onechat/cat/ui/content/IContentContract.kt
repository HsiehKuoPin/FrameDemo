package com.onechat.cat.ui.content

import com.benjamin.base.mvp.IContract.*
import com.onechat.cat.entity.AccountArticleEntity
import io.reactivex.Observable

/**
 * @describe
 * @author  Benjamin
 * @date 2019/5/28  17:22
 * 								 - generate by MvpAutoCodePlus plugin.
 */

interface IContentContract {
    interface View : IView {
        fun getAccountArticleSuccess(accountArticle: AccountArticleEntity)
        fun getAccountArticleFail(msg: String)
    }

    interface Presenter : IPresenter {
        fun getAccountArticle(id: Int, curPage: Int)
    }

    interface Model : IModel {
        fun getAccountArticle(id: Int, curPage: Int): Observable<AccountArticleEntity>
    }
}
