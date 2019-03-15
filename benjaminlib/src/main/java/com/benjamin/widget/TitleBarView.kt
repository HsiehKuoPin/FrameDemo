package com.benjamin.widget

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import com.benjamin.R

class TitleBarView(context: Context, attrs: AttributeSet?) : FrameLayout(context, attrs, 0), ITitleBarView {
    private var onTitleBarViewListener: OnTitleBarViewListener? = null

    override fun setOnTitleBarViewListener(listener: OnTitleBarViewListener) {
        onTitleBarViewListener = listener
    }

    private val ivBack: ImageView by bindView(R.id.iv_back)
    private val tvContent: TextView by bindView(R.id.tv_content)

//    var onTitleBarViewListener: OnTitleBarViewListener? = null

    override var title: String? = null
        set(value) {
            if (value != null) {
                tvContent.visibility = View.VISIBLE
                tvContent.text = value
            }
        }

    init {
        LayoutInflater.from(context).inflate(R.layout.base_title_bar, this, true)
        ivBack.setOnClickListener { onTitleBarViewListener?.onBackClick() }
    }

    private fun <T : View> View.bindView(id: Int): Lazy<T> {
        return lazy { findViewById<T>(id) }
    }
}


interface ITitleBarView {
    var title: String?
    fun setOnTitleBarViewListener(listener: OnTitleBarViewListener)
}

interface OnTitleBarViewListener {
    fun onBackClick()
}

