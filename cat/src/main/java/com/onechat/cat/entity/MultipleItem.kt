package com.onechat.cat.entity

import com.chad.library.adapter.base.entity.MultiItemEntity

class MultipleItem(private val itemType: Int) : MultiItemEntity {
    var banners: List<BannerEntity>? = null
    var articles: ArticleIntroEntity? = null

    override fun getItemType(): Int {
        return itemType
    }

    companion object {
        const val TYPE_BANNER = 1
        const val TYPE_MAIN = 2
    }
}