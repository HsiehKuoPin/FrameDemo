package com.onechat.cat.ui.home.adapter

import com.bumptech.glide.Glide
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.chad.library.adapter.base.util.MultiTypeDelegate
import com.onechat.cat.R
import com.onechat.cat.entity.ArticleIntroEntity
import com.onechat.cat.entity.BannerEntity

/**
 * @author Ben
 * @date 2019/7/15
 */
class HomeAdapter : BaseQuickAdapter<Any, BaseViewHolder>(null) {
    val TYPE_BANNER = 1
    val TYPE_MAIN = 2

    init {
        multiTypeDelegate = object : MultiTypeDelegate<Any>() {
            override fun getItemType(entity: Any): Int {
                //根据你的实体类来判断布局类型
                return if (entity is List<*>) TYPE_BANNER else TYPE_MAIN
            }
        }
                .registerItemType(TYPE_BANNER, R.layout.banner)
                .registerItemType(TYPE_MAIN, R.layout.adapter_article)
    }

    override fun convert(helper: BaseViewHolder, item: Any) {
        when (helper.itemViewType) {
            TYPE_BANNER -> {
                item as List<BannerEntity>
                Glide.with(mContext).load(item[0].imagePath).into(helper.getView(R.id.iv_banner))
            }
            TYPE_MAIN -> {
                item as ArticleIntroEntity
                helper.setText(R.id.tv_title, item.title)
                helper.setText(R.id.tv_time, "时间：" + item.niceDate)

            }
        }
    }
}