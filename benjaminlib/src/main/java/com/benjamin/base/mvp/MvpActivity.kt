package com.benjamin.base.mvp

import com.benjamin.R
import com.benjamin.base.BaseActivity
import com.benjamin.base.mvp.BaseContract.BasePresenter
import com.benjamin.base.mvp.BaseContract.BaseView
import com.benjamin.widget.OnTitleBarViewListener
import com.benjamin.widget.TitleBarView

/**
 * @author  Ben
 * @date 2019/1/17
 */
abstract class MvpActivity<P : BasePresenter> : BaseActivity(), OnTitleBarViewListener, BaseView {
    protected val titleBarView by bindViewNullable<TitleBarView>(R.id.app_tool_bar)
    protected var mPresenter: P? = null

    override fun initView() {
        super.initView()
        titleBarView?.setOnTitleBarViewListener(this)
    }

    override fun initData() {
        super.initData()
        mPresenter = getPresenter() as P
    }

    abstract fun getPresenter(): BasePresenter?

    override fun onBackClick() {
        finish()
    }

    override fun onTokenInvalid() {
    }

    override fun onDestroy() {
        super.onDestroy()
        mPresenter?.onDestroy()
    }

}