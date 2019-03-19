package com.kuopin.frame.ui.test.model

import com.kuopin.frame.entity.DeviceEntity
import com.kuopin.frame.net.NetProvider
import com.kuopin.frame.ui.test.contract.ITestContract
import io.reactivex.Observable

/**
 * @describe
 * @author  Benjamin
 * @date 2019/3/19  10:37
 * 								 - generate by MvpAutoCodePlus plugin.
 */

class TestModel : ITestContract.Model {
    override fun getDevice(macAddress: String): Observable<DeviceEntity> {
        return NetProvider.getInstance().getDevice(macAddress)
    }
}

