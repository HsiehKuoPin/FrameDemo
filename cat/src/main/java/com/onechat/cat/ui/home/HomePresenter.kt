package com.onechat.cat.ui.home

import com.benjamin.base.mvp.BasePresenter
import com.benjamin.utils.extension.io2main
import com.benjamin.utils.extension.subscribeByHandle
import com.onechat.cat.entity.AccountArticleEntity
import com.onechat.cat.entity.BannerEntity
import com.onechat.cat.entity.MultipleItem
import io.reactivex.Observable
import io.reactivex.functions.BiFunction

/**
 * @describe
 * @author  Benjamin
 * @date 2019/5/28  11:42
 * 								 - generate by MvpAutoCodePlus plugin.
 */

class HomePresenter : BasePresenter<IHomeContract.View, IHomeContract.Model>(), IHomeContract.Presenter {

    override fun onCreateModel(): IHomeContract.Model {
        return HomeModel()
    }

    override fun getHomes() {
        Observable.zip(mModel.getBanner(),
                mModel.getArticle(0),
                BiFunction<List<BannerEntity>, AccountArticleEntity, List<MultipleItem>> { t1, t2 ->
                    mutableListOf<MultipleItem>().apply {
                        add(MultipleItem(MultipleItem.TYPE_BANNER).apply { banners = t1 })

                        addAll(t2.datas.map { MultipleItem(MultipleItem.TYPE_MAIN).apply { articles = it } })
//                        add(t1)
//                        addAll(t2.datas)
                    }
                }).io2main()
                .subscribeByHandle(mView) {
                    mView?.getHomes(it)
                }

    }

}

