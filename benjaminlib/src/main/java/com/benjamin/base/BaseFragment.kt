package com.benjamin.base

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.benjamin.R
import com.benjamin.widget.loading.LoadingV
import com.benjamin.widget.loading.LoadingVFactory

abstract class BaseFragment : Fragment(){
    protected var TAG = ""
    protected abstract fun getLayoutId(): Int

    private var contentView: View? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        this.TAG = this.javaClass.simpleName
//        return if (getLayoutId() != 0) inflater.inflate(getLayoutId(), container, false)
        contentView = inflater.inflate(getLayoutId(), null)
        return contentView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initData()
    }

    open fun initView() {
    }

    open fun initData() {
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
        val loadingLayout = contentView?.findViewById<ViewGroup>(R.id.layout_loading)
        return if (loadingLayout == null) {
            LoadingV.DEFAULT_IMPL
        } else {
            LoadingVFactory().createEmbeddedDefaultLoadingV(loadingLayout)
        }
    }

    protected open fun onProgressShowing() {

    }

//    private fun <T : View> View.bindView(id: Int): Lazy<T> {
//        return lazy { findViewById<T>(id) }
//    }

}