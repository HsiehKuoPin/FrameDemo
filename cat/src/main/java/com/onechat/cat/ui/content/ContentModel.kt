package com.onechat.cat.ui.content

import com.onechat.cat.entity.AccountArticleEntity
import com.onechat.cat.net.NetProvider
import io.reactivex.Observable

/**
 * @describe
 * @author  Benjamin
 * @date 2019/5/28  17:22
 * 								 - generate by MvpAutoCodePlus plugin.
 */

class ContentModel : IContentContract.Model {
    override fun getAccountArticle(id: Int, curPage: Int): Observable<AccountArticleEntity> {
        return NetProvider.getInstance().getAccountArticle(id, curPage)
    }

}

