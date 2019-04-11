package com.onechat.cat.ui.register

import com.benjamin.base.mvp.BasePresenter
import com.benjamin.utils.extension.addTo
import com.benjamin.utils.extension.io2main
import com.benjamin.utils.extension.subscribeByHandle
import com.onechat.cat.entity.RequestEntity

/**
 * @describe
 * @author  Benjamin
 * @date 2019/4/9  16:47
 * 								 - generate by MvpAutoCodePlus plugin.
 */

class RegisterPresenter : BasePresenter<IRegisterContract.View, IRegisterContract.Model>(), IRegisterContract.Presenter {

    override fun onCreateModel(): IRegisterContract.Model {
        return RegisterModel()
    }

    override fun register(requestBody: RequestEntity) {
        mModel.register(requestBody)
                .io2main()
                .subscribeByHandle(
                        onSuccess = {
                            mView?.onRegisterSuccess(it)
                        },
                        onFailure = {
                            mView?.onRegisterFailure(it.exMessage)
                        }
                )
                .addTo(this)
    }

}

