package com.benjamin.http

import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

/**
 * @author  Ben
 * @date 2019/3/5
 * desc : Observables 和 Subscribers管理,防止内存泄漏
 */
open class RxManager {
    private var compositeDisposable:CompositeDisposable? = null

    /**
     * 添加observer
     * @param observer
     */
    fun addObserver(observer: Disposable) {
        synchronized(this) {
            if (compositeDisposable == null) {
                compositeDisposable = CompositeDisposable()
            }
        }
        compositeDisposable?.add(observer)
    }

    fun dispose() {
        if (!isDisposed()) compositeDisposable?.dispose()
    }

    fun isDisposed(): Boolean {
        return this.compositeDisposable == null || this.compositeDisposable!!.isDisposed
    }
}