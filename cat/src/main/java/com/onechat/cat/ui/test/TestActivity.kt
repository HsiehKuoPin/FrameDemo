package com.onechat.cat.ui.test

import android.content.Context
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import com.benjamin.base.BaseActivity
import com.benjamin.utils.eighteen.ActivityUtils
import com.benjamin.utils.eighteen.ToastUtils
import com.onechat.cat.R
import kotlinx.android.synthetic.main.activity_test.*

class TestActivity : BaseActivity() {
    private val dataList = mutableListOf<Int>()
    override fun getLayoutId(): Int {
        return R.layout.activity_test
    }

    override fun initView() {
        for (i in 1..100) {
            dataList.add(i)
        }
        rv.run {
            layoutManager = LinearLayoutManager(this@TestActivity)
            adapter = object : RecyclerView.Adapter<RegisterViewHolder>() {
                override fun onCreateViewHolder(p0: ViewGroup, p1: Int): RegisterViewHolder {
                    val view = LayoutInflater.from(p0.context).inflate(R.layout.adapter_test, p0, false)
                    return RegisterViewHolder(view)
                }

                override fun getItemCount(): Int {
                    return dataList.size
                }

                override fun onBindViewHolder(p0: RegisterViewHolder, p1: Int) {
                    p0.itemView.setOnClickListener { ToastUtils.showShort("itemClick:$p1") }
                }
            }
            layoutAnimation = AnimationUtils.loadLayoutAnimation(this@TestActivity, R.anim.layout_animation_fall_down)
        }
    }

    class RegisterViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    companion object {
        fun start(context: Context) {
            ActivityUtils.launchActivity(context, TestActivity::class.java)
        }
    }
}
