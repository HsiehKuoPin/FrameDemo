package com.kuopin.frame.ui.test.view

import com.benjamin.base.mvp.MvpActivity
import com.benjamin.utils.MacUtils
import com.benjamin.utils.eighteen.DeviceUtils
import com.benjamin.utils.eighteen.SystemUtil
import com.kuopin.frame.R
import com.kuopin.frame.entity.DeviceEntity
import com.kuopin.frame.ui.test.contract.ITestContract
import com.kuopin.frame.ui.test.presenter.TestPresenter
import com.orhanobut.logger.Logger
import kotlinx.android.synthetic.main.activity_test.*

/**
 * @describe
 * @author  Benjamin
 * @date 2019/3/19  10:37
 * 								 - generate by MvpAutoCodePlus plugin.
 */

class TestActivity : MvpActivity<ITestContract.Presenter>(), ITestContract.View {
    override fun getDeviceS(device: DeviceEntity) {
        Logger.d(device)
    }

    override fun getDeviceF(msg: String) {
        Logger.d(msg)
    }

    override fun getPresenter(): ITestContract.Presenter {
        return TestPresenter(this)
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_test
    }

    override fun initView() {
        super.initView()
        tv_test.setOnClickListener { mPresenter.getDevice(DeviceUtils.getMacAddress()) }
    }

}

