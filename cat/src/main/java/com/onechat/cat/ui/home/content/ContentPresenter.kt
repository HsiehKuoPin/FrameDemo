package com.onechat.cat.ui.home.content

import com.benjamin.base.mvp.BasePresenter
import com.benjamin.utils.extension.io2main
import com.benjamin.utils.extension.subscribeByHandle

/**
 * @describe
 * @author  Benjamin
 * @date 2019/5/28  17:22
 * 								 - generate by MvpAutoCodePlus plugin.
 */

class ContentPresenter : BasePresenter<IContentContract.View, IContentContract.Model>(), IContentContract.Presenter {
    override fun getAccountArticle(id: Int, curPage: Int) {
        mModel.getAccountArticle(id, curPage)
            .io2main()
            .subscribeByHandle(
                onSuccess = {
                    mView?.getAccountArticleSuccess(it)
                },
                onFailure = {
                    mView?.getAccountArticleFail(it.toString())
                }
            )
    }

    override fun onCreateModel(): IContentContract.Model {
        return ContentModel()
    }

}

