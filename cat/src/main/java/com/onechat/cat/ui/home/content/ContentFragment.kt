package com.onechat.cat.ui.home.content

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.benjamin.base.mvp.MvpFragment
import com.benjamin.utils.eighteen.ToastUtils
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.onechat.cat.R
import com.onechat.cat.entity.AccountEntity
import com.onechat.cat.ui.home.adapter.ContentFragment
import kotlinx.android.synthetic.main.content_fragment.*

/**
 * @describe
 * @author  Benjamin
 * @date 2019/5/28  17:22
 * 								 - generate by MvpAutoCodePlus plugin.
 */

class ContentFragment : MvpFragment<IContentContract.Presenter>(), IContentContract.View {
    override fun getLayoutId(): Int {
        return R.layout.content_fragment
    }

    override fun onCreatePresenter(): IContentContract.Presenter {
        return ContentPresenter()
    }

    override fun initView() {
        super.initView()
        val id = arguments?.getInt(KEY_ID)
        ToastUtils.showShort("id:$id")
        rv.layoutManager = LinearLayoutManager(context)
        val adapter = object : BaseQuickAdapter<List<AccountEntity>, BaseViewHolder>(R.layout.adapter_article){

            override fun convert(helper: BaseViewHolder?, item: List<AccountEntity>?) {
            }

        }
        rv.adapter = adapter
    }

    companion object {
        private const val KEY_ID = "key_id"
        fun newInstance(id: Int) = ContentFragment().apply { arguments = Bundle().apply { putInt(
            KEY_ID, id) } }
    }

}

