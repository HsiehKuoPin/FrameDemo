package com.onechat.cat.ui.account

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.benjamin.base.mvp.MvpFragment
import com.onechat.cat.R
import com.onechat.cat.entity.AccountEntity
import com.onechat.cat.ui.content.ContentFragment
import kotlinx.android.synthetic.main.fragment_home.*

/**
 * @describe
 * @author  Benjamin
 * @date 2019/7/9  17:39
 * 								 - generate by MvpAutoCodePlus plugin.
 */

class AccountFragment : MvpFragment<IAccountContract.Presenter>(), IAccountContract.View {

    private val titles = mutableListOf<CharSequence>()
    private val fragments = mutableListOf<Fragment>()
    private val adapter by lazy { ViewPagerFragmentAdapter(childFragmentManager, fragments, titles) }
    override fun onCreatePresenter(): IAccountContract.Presenter {
        return AccountPresenter()
    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_account
    }

    override fun initView() {
        super.initView()
        tl_accounts.setupWithViewPager(vp_content)
        vp_content.adapter = adapter
    }

    override fun onHiddenChanged(hidden: Boolean) {
        super.onHiddenChanged(hidden)
        if (!hidden) showProgressView()
    }

    override fun onProgressShowing() {
        super.onProgressShowing()
        mPresenter.getAccounts()
    }

    override fun getAccountsSuccess(accounts: List<AccountEntity>) {
        titles.clear()
        fragments.clear()
        for (account in accounts) {
            fragments.add(ContentFragment.newInstance(account.id))
            titles.add(account.name)
        }
        adapter.run {
            listTitle = titles
            listFragment = fragments
            notifyDataSetChanged()
        }
    }

    class ViewPagerFragmentAdapter(
        fm: FragmentManager,
        var listFragment: List<Fragment>,
        var listTitle: List<CharSequence>
    ) : FragmentPagerAdapter(fm) {

        override fun getItem(position: Int): Fragment {
            return listFragment[position]
        }


        override fun getCount(): Int {
            return listFragment.size
        }

        override fun getPageTitle(position: Int): CharSequence? {
            return if (!listTitle.isNullOrEmpty()) {
                listTitle[position]
            } else super.getPageTitle(position)
        }

    }



    companion object {
        const val KEY_VALUE = "key_value"

        fun newInstance(value: String = ""): AccountFragment {
            return AccountFragment().apply { arguments = Bundle().apply { putString(KEY_VALUE, value) } }
        }
    }

}

