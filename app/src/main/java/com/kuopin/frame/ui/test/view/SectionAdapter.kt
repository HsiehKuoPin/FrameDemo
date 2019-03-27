package com.kuopin.frame.ui.test.view

import com.chad.library.adapter.base.BaseSectionQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.kuopin.frame.R

class SectionAdapter(layoutResId: Int, sectionHeadResId: Int, data: List<MySection>) : BaseSectionQuickAdapter<MySection, BaseViewHolder>(layoutResId, sectionHeadResId, data) {

    override fun convertHead(helper: BaseViewHolder, item: MySection) {
        helper.setText(R.id.tv_section, item.header)
    }

    override fun convert(helper: BaseViewHolder, item: MySection) {
        helper.setText(R.id.tv_content, item.t.itemName)
        helper.addOnClickListener(R.id.tv_content)
    }

}