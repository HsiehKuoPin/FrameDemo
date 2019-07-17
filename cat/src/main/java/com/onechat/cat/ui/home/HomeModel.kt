package com.onechat.cat.ui.home

import com.onechat.cat.entity.AccountArticleEntity
import com.onechat.cat.entity.BannerEntity
import com.onechat.cat.net.NetProvider
import io.reactivex.Observable

/**
 * @describe
 * @author  Benjamin
 * @date 2019/5/28  11:42
 * 								 - generate by MvpAutoCodePlus plugin.
 */

class HomeModel : IHomeContract.Model {
    override fun getBanner(): Observable<List<BannerEntity>> {
        return NetProvider.getInstance().getBanner()
    }

    override fun getArticle(id: Int): Observable<AccountArticleEntity> {
        return NetProvider.getInstance().getArticle(id)
    }

}

