package com.kuopin.frame.ui.test.view

import android.support.v7.widget.GridLayoutManager
import com.benjamin.base.mvp.MvpActivity
import com.benjamin.utils.eighteen.DeviceUtils
import com.kuopin.frame.R
import com.kuopin.frame.entity.DeviceEntity
import com.kuopin.frame.entity.TestEntity
import com.kuopin.frame.ui.test.contract.ITestContract
import com.kuopin.frame.ui.test.presenter.TestPresenter
import com.orhanobut.logger.Logger
import kotlinx.android.synthetic.main.activity_test.*

/**
 * @describe
 * @author  Benjamin
 * @date 2019/3/19  10:37
 *
 */

class TestActivity : MvpActivity<ITestContract.Presenter>(), ITestContract.View {
    private val dataList = mutableListOf<MySection>()
    private val sectionAdapter by lazy { SectionAdapter(R.layout.item_text, R.layout.item_section_content, dataList) }

    override fun getPresenter(): ITestContract.Presenter {
        return TestPresenter(this)
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_test
    }

    override fun initView() {
        super.initView()
        rv_section.layoutManager = GridLayoutManager(this, 4)
        rv_section.adapter = sectionAdapter
        tv_test1.setOnClickListener { mPresenter.getDevice(DeviceUtils.getMacAddress()!!) }
//        tv_test2.setOnClickListener { mPresenter.getDeviceWithHost(DeviceUtils.getMacAddress()!!) }
        tv_test2.setOnClickListener {
            for (i in 1..2) {
                dataList.add(MySection(true, "groupName$i"))
                dataList.add(MySection(TestEntity("item$i", "groupName$i")))
                dataList.add(MySection(TestEntity("item$i", "groupName$i")))
                dataList.add(MySection(TestEntity("item$i", "groupName$i")))
                if (i > 1) dataList.add(MySection(TestEntity("item$i", "groupName$i")))
            }
            sectionAdapter.setNewData(dataList)

        }

    }

    override fun getDeviceS(device: DeviceEntity) {
        Logger.d(device)
    }

    override fun getDeviceF(msg: String) {
        Logger.d(msg)
    }
}

