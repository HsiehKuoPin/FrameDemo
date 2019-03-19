package com.benjamin.base.mvp

import com.benjamin.R
import com.benjamin.base.BaseActivity
import com.benjamin.base.mvp.IContract.IPresenter
import com.benjamin.base.mvp.IContract.IView
import com.benjamin.widget.OnTitleBarViewListener
import com.benjamin.widget.TitleBarView

/**
 * @author  Ben
 * @date 2019/1/17
 */
abstract class MvpActivity<P : IPresenter> : BaseActivity(), OnTitleBarViewListener, IView {
    protected val titleBarView by bindViewNullable<TitleBarView>(R.id.app_tool_bar)
    protected var mPresenter = getPresenter()

    override fun initView() {
        super.initView()
        titleBarView?.setOnTitleBarViewListener(this)
    }

    abstract fun getPresenter(): P

    override fun onBackClick() {
        finish()
    }

    override fun onTokenInvalid() {
    }

    override fun onDestroy() {
        mPresenter.onDestroy()
        super.onDestroy()
    }

}