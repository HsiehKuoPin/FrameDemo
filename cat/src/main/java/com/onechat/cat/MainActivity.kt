package com.onechat.cat

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.onechat.cat.ui.test.TestActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        tv_hello.setOnClickListener { TestActivity.start(this) }
    }
}
