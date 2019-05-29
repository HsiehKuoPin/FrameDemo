package com.benjamin.base

import android.app.Activity
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.view.ViewGroup
import com.benjamin.R
import com.benjamin.app.AppActivityManager
import com.benjamin.widget.loading.LoadingV
import com.benjamin.widget.loading.LoadingVFactory

abstract class BaseActivity : AppCompatActivity() {
    var TAG = this.javaClass.name

    protected val activity: Activity
        get() = this

    protected abstract fun getLayoutId(): Int

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AppActivityManager.appManager.addActivity(this)
//        AppConfig.init(this)
        setContentView(getLayoutId())
        initView()
        initData()
    }

    open fun initView() {
    }

    open fun initData() {
    }

    open val appLoadingV: LoadingV by lazy { createDialogLoadingV() }

    open fun createDialogLoadingV(): LoadingV {
        return LoadingVFactory().createDialogDefaultLoadingV(this)
    }

    protected val loadingV: LoadingV by lazy {
        createLoadingV().apply {
            setOnRefreshingListener {
                if (loadingV.isProgressShowing.not()) {
                    loadingV.showProgressView()
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

    override fun onDestroy() {
        AppActivityManager.appManager.removeActivity(this)
        super.onDestroy()
    }

    protected fun <V : View> bindView(id: Int): Lazy<V> = lazy { findViewById<V>(id) }
    protected fun <V : View> bindViewNullable(id: Int): Lazy<V?> = lazy { findViewById<V>(id) }

}