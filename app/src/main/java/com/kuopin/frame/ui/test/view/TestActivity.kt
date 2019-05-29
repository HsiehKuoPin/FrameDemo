package com.kuopin.frame.ui.test.view

import android.os.SystemClock
import android.support.v7.widget.GridLayoutManager
import android.view.View
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

class TestActivity : MvpActivity<TestPresenter>(), ITestContract.View {
    private val dataList = mutableListOf<MySection>()
    private val sectionAdapter by lazy { SectionAdapter(R.layout.item_text, R.layout.item_section_content, dataList) }

    override fun onCreatePresenter(): TestPresenter{
        return TestPresenter()
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_test
    }

    override fun initView() {
        super.initView()
        rv_section.layoutManager = GridLayoutManager(this, 4)
        rv_section.adapter = sectionAdapter
        tv_test1.setOnClickListener {
            mPresenter.getDevice(DeviceUtils.getMacAddress()!!)
//            MainActivity.start(this)
        }
//        tv_test2.setOnClickListener { mPresenter.getDeviceWithHost(DeviceUtils.getMacAddress()!!) }
        tv_test2.setOnClickListener {
            for (i in 1..5) {
                dataList.add(MySection(true, "groupName$i"))
                dataList.add(MySection(TestEntity("item$i", "groupName$i")))
                dataList.add(MySection(TestEntity("item$i", "groupName$i")))
                dataList.add(MySection(TestEntity("item$i", "groupName$i")))
                dataList.add(MySection(TestEntity("item$i", "groupName$i")))
                if (i > 1) dataList.add(MySection(TestEntity("item$i", "groupName$i")))
            }
            sectionAdapter.setNewData(dataList)

        }
//        tv_test2.performClick()
        rv_section.addOnScrollListener(object : RecyclerViewScrollListener() {
            override fun onScrollToBottom() {
                super.onScrollToBottom()
                tv_more.visibility = View.VISIBLE
                Thread {
                    SystemClock.sleep(1000)
                    runOnUiThread {
                        tv_more.visibility = View.GONE
                        sectionAdapter.addData(MySection(TestEntity("itemMore", "")))
                    }
                }.start()
            }
        })

    }

    override fun getDeviceS(device: DeviceEntity) {
        Logger.d(device)
    }

    override fun getDeviceF(msg: String) {
        Logger.d(msg)
    }
}

