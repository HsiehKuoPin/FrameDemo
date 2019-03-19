package com.kuopin.frame.ui.test.contract

import com.benjamin.base.mvp.IContract.*
import com.kuopin.frame.entity.DeviceEntity
import io.reactivex.Observable

/**
 * @describe
 * @author  Benjamin
 * @date 2019/3/19  10:37
 * 								 - generate by MvpAutoCodePlus plugin.
 */

interface ITestContract {
    interface View : IView {
        fun getDeviceS(device: DeviceEntity)
        fun getDeviceF(msg: String)
    }

    interface Presenter : IPresenter {
        fun getDevice(macAddress: String)
        fun getDeviceWithHost(macAddress: String)
    }

    interface Model : IModel {
        fun getDevice(macAddress: String): Observable<DeviceEntity>
        fun getDeviceWithHost(macAddress: String): Observable<DeviceEntity>
    }
}
