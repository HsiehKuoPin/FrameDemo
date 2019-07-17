package com.onechat.cat.ui.home.adapter

import com.bumptech.glide.Glide
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.onechat.cat.R
import com.onechat.cat.entity.MultipleItem

class MultipleItemQuickAdapter(data: List<MultipleItem>?) : BaseMultiItemQuickAdapter<MultipleItem, BaseViewHolder>(data) {
    init {
        addItemType(MultipleItem.TYPE_BANNER, R.layout.banner)
        addItemType(MultipleItem.TYPE_MAIN, R.layout.adapter_article)
    }


    override fun convert(helper: BaseViewHolder, item: MultipleItem) {
        when (helper.itemViewType) {
            MultipleItem.TYPE_BANNER -> {
                val banners = item.banners!!
                Glide.with(mContext).load(banners[1].imagePath).into(helper.getView(R.id.iv_banner) )
            }
            MultipleItem.TYPE_MAIN -> {
                val articles = item.articles!!
                helper.setText(R.id.tv_title, articles.title)
                        .setText(R.id.tv_time, "时间：" + articles.niceDate)
            }
        }
    }

}