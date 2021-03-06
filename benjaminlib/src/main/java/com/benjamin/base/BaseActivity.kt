package com.benjamin.base

import android.app.Activity
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.view.ViewGroup
import com.benjamin.R
import com.benjamin.app.AppActivityManager
import com.benjamin.widget.OnTitleBarViewListener
import com.benjamin.widget.TitleBarView
import com.benjamin.widget.loading.LoadingV
import com.benjamin.widget.loading.LoadingVFactory

abstract class BaseActivity : AppCompatActivity(), OnTitleBarViewListener {
    var TAG = this.javaClass.name
    protected val titleBarView by bindViewNullable<TitleBarView>(R.id.app_tool_bar)

    protected val activity: Activity
        get() = this

    protected abstract fun getLayoutId(): Int

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AppActivityManager.appManager.addActivity(this)
//        AppConfig.init(this)
        setContentView(getLayoutId())
        titleBarView?.setOnTitleBarViewListener(this)
        initView()
        initData()
    }

    override fun onBackClick() {
        finish()
    }
    open fun initView() {
    }

    open fun initData() {
    }

    open val loadingDialog: LoadingV by lazy { createDialogLoadingV() }

    open fun createDialogLoadingV(): LoadingV {
        return LoadingVFactory().createDialogDefaultLoadingV(this)
    }

    protected val loadingView: LoadingV by lazy {
        createLoadingV().apply {
            setOnRefreshingListener {
                if (loadingView.isProgressShowing.not()) {
                    loadingView.showProgressView()
                } else {
                    onProgressShowing()
                }
            }
        }
    }

    protected open fun createLoadingV(): LoadingV {
        val loadingLayout = findViewById<ViewGroup>(R.id.layout_loading)
        return if (loadingLayout == null) {
            LoadingV.DEFAULT_IMPL
        } else {
            LoadingVFactory().createEmbeddedDefaultLoadingV(loadingLayout)
        }
    }

    protected open fun onProgressShowing() {

    }

    /**
     * 隐藏View
     * @param views 视图
     */
    fun gone(vararg views: View) {
        if (views.isNotEmpty()) {
            for (view in views) {
                view.visibility = View.GONE
            }
        }
    }

    /**
     * 显示View 不占位置
     * @param views 视图
     */
    fun visible(vararg views: View) {
        if (views.isNotEmpty()) {
            for (view in views) {
                view.visibility = View.VISIBLE
            }
        }
    }

    /**
     * 显示View
     * @param views 视图
     */
    fun inVisible(vararg views: View) {
        if (views.isNotEmpty()) {
            for (view in views) {
                view.visibility = View.INVISIBLE
            }
        }
    }

    override fun onStart() {
        super.onStart()
//        StatusBarUtil.hideStatusNavigationBar(this.window)
    }

    override fun onDestroy() {
        AppActivityManager.appManager.removeActivity(this)
        super.onDestroy()
    }

    protected fun <V : View> bindView(id: Int): Lazy<V> = lazy { findViewById<V>(id) }
    protected fun <V : View> bindViewNullable(id: Int): Lazy<V?> = lazy { findViewById<V>(id) }

}