package com.benjamin.widget.loading.progress

import android.content.Context
import android.graphics.drawable.AnimationDrawable
import android.view.View
import android.widget.FrameLayout
import android.widget.ImageView
import com.benjamin.R

class LogoProgressView(context: Context) : FrameLayout(context) {

    init {
        View.inflate(context, R.layout.layout_progress, this)
        val ivLogo: ImageView = findViewById(R.id.iv_logo)
        (ivLogo.drawable as AnimationDrawable).start()
    }
}