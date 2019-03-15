package com.benjamin.http

import com.benjamin.http.exception.HttpException
import com.benjamin.http.exception.HttpExceptionHandler
import io.reactivex.observers.DisposableObserver

/**
 * @author  Ben
 * @date 2018/12/29
 */
open abstract class ErrorHandleDisposableObserver<T> : DisposableObserver<T>() {
    override fun onComplete() {
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