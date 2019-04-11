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

    fun add(disposable: Disposable) {
        synchronized(this) {
            if (compositeDisposable == null) {
                compositeDisposable = CompositeDisposable()
            }
        }
        compositeDisposable?.add(disposable)
    }

    fun dispose() {
        if (!isDisposed()) compositeDisposable?.dispose()
    }

    fun isDisposed(): Boolean {
        return this.compositeDisposable == null || this.compositeDisposable!!.isDisposed
    }
}