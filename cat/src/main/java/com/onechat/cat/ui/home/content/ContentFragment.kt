package com.onechat.cat.ui.home.content

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.benjamin.base.mvp.MvpFragment
import com.benjamin.utils.DateUtil
import com.benjamin.utils.eighteen.ToastUtils
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.onechat.cat.R
import com.onechat.cat.entity.AccountArticleEntity
import com.onechat.cat.entity.ArticleIntroEntity
import com.onechat.cat.ui.web.WebActivity
import kotlinx.android.synthetic.main.content_fragment.*

/**
 * @describe
 * @author  Benjamin
 * @date 2019/5/28  17:22
 * 								 - generate by MvpAutoCodePlus plugin.
 */

class ContentFragment : MvpFragment<IContentContract.Presenter>(), IContentContract.View {
    private var curPage = 1

    private val adapter = object : BaseQuickAdapter<ArticleIntroEntity, BaseViewHolder>(R.layout.adapter_article) {

        override fun convert(helper: BaseViewHolder, item: ArticleIntroEntity) {
            helper.setText(R.id.tv_title, item.title)
            helper.setText(R.id.tv_time, "时间：" + DateUtil.getfriendlyTime(item.publishTime) )
        }

    }

    override fun getLayoutId(): Int {
        return R.layout.content_fragment
    }

    override fun onCreatePresenter(): IContentContract.Presenter {
        return ContentPresenter()
    }

    override fun initView() {
        super.initView()
        rv.layoutManager = LinearLayoutManager(context)
        rv.adapter = adapter
        adapter.setOnItemClickListener { adapter, view, position ->
            WebActivity.start(context!!, (adapter.data[position] as ArticleIntroEntity).link!!)
        }
    }

    override fun initData() {
        super.initData()
        val id = arguments!!.getInt(KEY_ID)
//        appLoadingV.showProgressView()
        mPresenter.getAccountArticle(id, curPage)
    }

    override fun getAccountArticleSuccess(accountArticle: AccountArticleEntity) {
//        appLoadingV.showContentView()
        adapter.setNewData(accountArticle.datas)
    }

    override fun getAccountArticleFail(msg: String) {
        appLoadingV.showContentView()
        ToastUtils.showShort(msg)
    }

    companion object {
        private const val KEY_ID = "key_id"
        @JvmStatic
        fun newInstance(id: Int) = ContentFragment().apply { arguments = Bundle().apply { putInt(KEY_ID, id) } }
    }

}

