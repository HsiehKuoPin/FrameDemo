package com.onechat.cat.ui.home

import android.os.Bundle
import android.support.v4.app.Fragment
import com.benjamin.base.mvp.MvpFragment
import com.benjamin.utils.eighteen.ToastUtils
import com.onechat.cat.R
import com.onechat.cat.entity.AccountEntity
import com.onechat.cat.ui.content.ContentFragment
import com.onechat.cat.ui.home.adapter.ViewPagerFragmentAdapter
import kotlinx.android.synthetic.main.fragment_home.*


/**
 * @describe
 * @author  Benjamin
 * @date 2019/5/28  11:42
 * 								 - generate by MvpAutoCodePlus plugin.
 */

class HomeFragment : MvpFragment<IHomeContract.Presenter>(), IHomeContract.View {

    private var viewPagerFragmentAdapter: ViewPagerFragmentAdapter? = null
    private val titles = mutableListOf<CharSequence>()
    private val fragments = mutableListOf<Fragment>()

    override fun getLayoutId(): Int {
        return R.layout.fragment_home
    }

    override fun onCreatePresenter(): IHomeContract.Presenter {
        return HomePresenter()
    }

    override fun initView() {
        super.initView()
        tl_accounts.setupWithViewPager(vp_content)
        viewPagerFragmentAdapter = ViewPagerFragmentAdapter(childFragmentManager, fragments, titles)
        vp_content.adapter = viewPagerFragmentAdapter
    }

    override fun onHiddenChanged(hidden: Boolean) {
        super.onHiddenChanged(hidden)
        if (!hidden) loadingView.showProgressView()
    }

    override fun onProgressShowing() {
        mPresenter.getAccounts()
    }

    override fun getAccountsSuccess(accounts: List<AccountEntity>) {
        loadingView.showContentView()
        for (account in accounts) {
            fragments.add(ContentFragment.newInstance(account.id))
            titles.add(account.name)
        }
        viewPagerFragmentAdapter?.run {
            listTitle = titles
            listFragment = fragments
            notifyDataSetChanged()
        }
    }

    override fun getAccountsFail(msg: String) {
        loadingView.showErrorView(msg)
        ToastUtils.showShort(msg)
    }

    companion object {
        const val KEY_VALUE = "key_value"

        fun newInstance(value: String = ""): HomeFragment {
            return HomeFragment().apply { arguments = Bundle().apply { putString(KEY_VALUE, value) } }
        }
    }

}

