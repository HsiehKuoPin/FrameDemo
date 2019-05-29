package com.onechat.cat.ui.main

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentTransaction
import com.benjamin.base.BaseActivity
import com.benjamin.utils.eighteen.ToastUtils
import com.onechat.cat.R
import com.onechat.cat.ui.home.HomeFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {
    // macAddress: 90:09:19:cb:d7:6d 201905007
    private val homeFragment = HomeFragment.newInstance()
    private val categoryFragment = HomeFragment.newInstance()
    private val recommendFragment = HomeFragment.newInstance()
    private val mineFragment = HomeFragment.newInstance()
    override fun getLayoutId(): Int {
        return R.layout.activity_main
    }

    override fun initView() {
        super.initView()
        rb_home.setOnClickListener { showFragment(homeFragment) }
        rb_category.setOnClickListener { showFragment(categoryFragment) }
        rb_recommend.setOnClickListener { showFragment(recommendFragment) }
        rb_mine.setOnClickListener { showFragment(mineFragment) }
        rb_home.performClick()
    }

    private var clickBackTime: Long = 0
    private var lastClickBackTime: Long = 0
    override fun onBackPressed() {
        clickBackTime = System.currentTimeMillis()
        if (clickBackTime - lastClickBackTime > 2000) {
            ToastUtils.showShort("再点一下退出~")
        } else {
            super.onBackPressed()
        }
        lastClickBackTime = clickBackTime
    }

    private fun showFragment(fragment: Fragment) {
        if (fragment.isVisible) return
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        hideAllFragment(fragmentTransaction)
        if (fragment.isAdded) {
            fragmentTransaction.show(fragment).commit()
        } else {
            fragmentTransaction.add(R.id.fl_content, fragment).show(fragment).commit()
        }
    }

    private fun hideAllFragment(fragmentTransaction: FragmentTransaction) {
        fragmentTransaction.hide(homeFragment)
        fragmentTransaction.hide(categoryFragment)
        fragmentTransaction.hide(recommendFragment)
        fragmentTransaction.hide(mineFragment)
    }

}
