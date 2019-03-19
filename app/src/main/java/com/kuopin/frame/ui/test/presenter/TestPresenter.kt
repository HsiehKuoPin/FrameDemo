package com.kuopin.frame.ui.test.presenter

import com.benjamin.base.mvp.BasePresenter
import com.benjamin.utils.extension.addTo
import com.benjamin.utils.extension.io2main
import com.benjamin.utils.extension.subscribeByHandle
import com.kuopin.frame.ui.test.contract.ITestContract
import com.kuopin.frame.ui.test.model.TestModel

/**
 * @describe
 * @author  Benjamin
 * @date 2019/3/19  10:37
 * 								 - generate by MvpAutoCodePlus plugin.
 */

class TestPresenter(view: ITestContract.View?) : BasePresenter<ITestContract.View, ITestContract.Model>(view),
    ITestContract.Presenter {

    override fun onCreateModel(): ITestContract.Model {
        return TestModel()
    }

    override fun getDevice(macAddress: String) {
        mModel.getDevice(macAddress)
            .io2main()
            .subscribeByHandle(
                onSuccess = {
                    mView?.getDeviceS(it)
                },
                onFailure = {
                    mView?.getDeviceF(it.exMessage)
                }
            )
            .addTo(this)
    }
}

