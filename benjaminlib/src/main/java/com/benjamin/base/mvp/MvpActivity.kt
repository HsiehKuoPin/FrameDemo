package com.benjamin.base.mvp

import android.os.Bundle
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
    protected lateinit var mPresenter: P

    override fun onCreate(savedInstanceState: Bundle?) {
        mPresenter = onCreatePresenter()
        mPresenter.attachView(this)
        titleBarView?.setOnTitleBarViewListener(this)
        super.onCreate(savedInstanceState)
    }

    abstract fun onCreatePresenter(): P

    override fun onBackClick() {
        finish()
    }

    override fun onTokenInvalid() {
    }

    override fun onDestroy() {
        mPresenter.unsubscribe()
        mPresenter.detachView()
        super.onDestroy()
    }

}