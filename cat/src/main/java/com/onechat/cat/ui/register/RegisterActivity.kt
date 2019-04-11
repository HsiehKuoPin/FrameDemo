package com.onechat.cat.ui.register

import android.content.Context
import com.benjamin.base.mvp.MvpActivity
import com.benjamin.utils.eighteen.ActivityUtils
import com.onechat.cat.R
import com.onechat.cat.entity.MemberEntity
import com.onechat.cat.entity.RequestEntity
import com.orhanobut.logger.Logger
import kotlinx.android.synthetic.main.activity_register.*


/**
 * @describe
 * @author  Benjamin
 * @date 2019/4/9  16:47
 * 								 - generate by MvpAutoCodePlus plugin.
 */

class RegisterActivity : MvpActivity<IRegisterContract.Presenter>(), IRegisterContract.View {
    override fun getLayoutId(): Int {
        return R.layout.activity_register
    }

    override fun onCreatePresenter(): IRegisterContract.Presenter {
        return RegisterPresenter()
    }

    override fun initData() {
        super.initData()
        tv_register.setOnClickListener {
            mPresenter.register(RequestEntity().apply {
                phone = "15966668888"
                code = "123456"
                accountType = "password"
                password = "123456"
                channelCode = "101"
                inviterUUID = "1234"
            })
        }
    }

    override fun onRegisterSuccess(member: MemberEntity) {
        Logger.d(member)

    }

    override fun onRegisterFailure(exMessage: String?) {
        Logger.d(exMessage)
    }

    companion object {
        fun start(context: Context) {
            ActivityUtils.launchActivity(context, RegisterActivity::class.java)
        }
    }
}

