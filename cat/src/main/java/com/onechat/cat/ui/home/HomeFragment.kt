package com.onechat.cat.ui.home

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.benjamin.base.mvp.MvpFragment
import com.onechat.cat.R
import com.onechat.cat.entity.MultipleItem
import com.onechat.cat.ui.home.adapter.MultipleItemQuickAdapter
import kotlinx.android.synthetic.main.fragment_home.*


/**
 * @describe
 * @author  Benjamin
 * @date 2019/5/28  11:42
 * 								 - generate by MvpAutoCodePlus plugin.
 */

class HomeFragment : MvpFragment<IHomeContract.Presenter>(), IHomeContract.View {
//    private val homeAdapter by lazy { HomeAdapter() }
    private val homeAdapter by lazy { MultipleItemQuickAdapter(null) }
    override fun getLayoutId(): Int {
        return R.layout.fragment_home
    }

    override fun onCreatePresenter(): IHomeContract.Presenter {
        return HomePresenter()
    }

    override fun initView() {
        super.initView()
        rv_home.layoutManager = LinearLayoutManager(context)
        rv_home.adapter = homeAdapter
    }

    override fun onHiddenChanged(hidden: Boolean) {
        super.onHiddenChanged(hidden)
        if (!hidden) loadingView.showProgressView()
    }

    override fun onProgressShowing() {
        mPresenter.getHomes()
    }

    override fun getHomes(datas: List<MultipleItem>) {
        homeAdapter.setNewData(datas)
    }


    companion object {
        const val KEY_VALUE = "key_value"

        fun newInstance(value: String = ""): HomeFragment {
            return HomeFragment().apply { arguments = Bundle().apply { putString(KEY_VALUE, value) } }
        }
    }

}

