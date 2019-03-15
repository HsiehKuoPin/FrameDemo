package com.benjamin.http

import com.benjamin.http.exception.HttpException
import com.benjamin.http.exception.HttpExceptionHandler
import io.reactivex.Observer
import io.reactivex.disposables.Disposable

/**
 * @author  Ben
 * @date 2018/12/29
 */
open class ErrorHandleObserver<T> : Observer<T> {
    override fun onComplete() {
    }

    override fun onSubscribe(d: Disposable) {
    }

    override fun onNext(t: T) {
        onSuccess(t)
    }

    override fun onError(e: Throwable) {
        onFailure(HttpExceptionHandler.handle(e))
    }

    protected open fun onSuccess(t: T){}

    protected open fun onFailure(exception: HttpException){}
}