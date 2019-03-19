package com.benjamin.utils.extension

import com.benjamin.base.mvp.IContract.IView
import com.benjamin.http.ErrorHandleDisposableObserver
import com.benjamin.http.RxManager
import com.benjamin.http.exception.HttpException
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers.mainThread
import io.reactivex.disposables.Disposable
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers

/**
 * @author  Ben
 * @date 2018/12/29
 */
fun Disposable.addTo(rxManager: RxManager) {
    rxManager.addObserver(this)
}

fun <T> Observable<T>.io2main(): Observable<T> {
    return compose { upstream -> upstream.subscribeOn(Schedulers.io()).observeOn(mainThread()) }
}

//fun <T> Observable<T>.io2main(): ObservableTransformer<T, T> {
//    return ObservableTransformer { upstream -> upstream.subscribeOn(Schedulers.io()).observeOn(mainThread()) }
//}

fun <T> Observable<T>.subscribeByHandle(view: IView? = null, onSuccess: (data: T) -> Unit): Disposable {
    return subscribeByHandle(view,onSuccess, {})
}

fun <T> Observable<T>.subscribeByHandle(view: IView? = null, onSuccess: (data: T) -> Unit, onFailure: (e: HttpException) -> Unit): Disposable {
    return subscribeWithErrorHandle(view,onSuccess, onFailure)
}

//private fun <T> Observable<T>.subscribe(view: IView?,onSuccess: (data: T) -> Unit, onFailure: (e: HttpException) -> Unit) {
//    return subscribe(object : ErrorHandleObserver<T>() {
//        override fun onSuccess(t: T) {
//            onSuccess.invoke(t)
//        }
//
//        override fun onFailure(exception: HttpException) {
//            if (exception.exCode == 9999)view?.onTokenInvalid()
//            onFailure.invoke(exception)
//        }
//
//    })
//}
private fun <T> Observable<T>.subscribeWithErrorHandle(view: IView?, onSuccess: (data: T) -> Unit, onFailure: (e: HttpException) -> Unit): Disposable {
    return subscribeWith(object : ErrorHandleDisposableObserver<T>() {
        override fun onSuccess(t: T) {
            onSuccess.invoke(t)
        }

        override fun onFailure(exception: HttpException) {
            if (exception.exCode == 9999)view?.onTokenInvalid()
            onFailure.invoke(exception)
        }

    })
}


fun <T> Observable<T>.subscribeBy(view: IView? = null, onSuccess: (data: T) -> Unit): Disposable {
    return subscribeWith(view,onSuccess, {})
}

fun <T> Observable<T>.subscribeBy(view: IView? = null, onSuccess: (data: T) -> Unit, onFailure: (e: Throwable) -> Unit): Disposable {
    return subscribeWith(view,onSuccess, onFailure)
}

private fun <T> Observable<T>.subscribeWith(view: IView?, onSuccess: (data: T) -> Unit, onFailure: (e: Throwable) -> Unit): Disposable {
    return subscribeWith(object : DisposableObserver<T>() {
        override fun onComplete() {
        }

        override fun onNext(t: T) {
            onSuccess(t)
        }

        override fun onError(e: Throwable) {
            onFailure(e)
        }

    })
}
