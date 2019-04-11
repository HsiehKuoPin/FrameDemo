package com.onechat.cat.ui.register

import com.benjamin.base.mvp.IContract.*
import com.onechat.cat.entity.MemberEntity
import com.onechat.cat.entity.RequestEntity
import io.reactivex.Observable

/**
 * @describe
 * @author  Benjamin
 * @date 2019/4/9  16:47
 * 								 - generate by MvpAutoCodePlus plugin.
 */

interface IRegisterContract {
    interface View : IView {
        fun onRegisterSuccess(member: MemberEntity)
        fun onRegisterFailure(exMessage: String?)
    }

    interface Presenter : IPresenter {
        fun register(requestBody: RequestEntity)
    }

    interface Model : IModel {
        fun register(requestBody: RequestEntity): Observable<MemberEntity>
    }
}
