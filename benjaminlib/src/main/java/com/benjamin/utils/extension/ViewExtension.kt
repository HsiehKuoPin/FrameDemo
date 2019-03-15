package com.benjamin.utils.extension

import android.view.View
import android.view.ViewTreeObserver
import android.widget.LinearLayout

/**
 * View 扩展函数
 * @author  Ben
 * @date 2018/12/27
 */


//fun <V : View> View.bindView(id: Int): Lazy<V> = lazy { findViewById<V>(id) }
//
//fun <V : View> View.bindViewNullable(id: Int): Lazy<V?> = lazy { findViewById<V?>(id) }

/**
 * 判断控件是否消失
 */
fun View.isGone(): Boolean {
    return visibility == View.GONE
}

/**
 * 设置控件消失
 */
fun View.gone() {
    visibility = View.GONE
}

/**
 * 判断控件是否显示
 */
fun View.isVisible(): Boolean {
    return visibility == View.VISIBLE
}

/**
 * 显示控件
 */
fun View.show() {
    visibility = View.VISIBLE
}

/**
 * 判断控件是否隐藏
 */
fun View.isInvisible(): Boolean {
    return visibility == View.INVISIBLE
}

/**
 * 隐藏控件
 */
fun View.hide() {
    visibility = View.INVISIBLE
}

/**
 * 设置控件宽
 * @param width
 */
fun View.setWidth(width: Int) {
    val params = layoutParams as LinearLayout.LayoutParams
    params.width = width
    layoutParams = params
}

/**
 * 设置控件高
 * @param height
 */
fun View.setHeight(height: Int) {
    val params = layoutParams as LinearLayout.LayoutParams
    params.height = height
    layoutParams = params
}

/**
 * 直接获取控件的宽、高
 * @return IntArray
 */
fun View.getWidgetWH(): IntArray {
    viewTreeObserver.addOnGlobalLayoutListener(object : ViewTreeObserver.OnGlobalLayoutListener {
        override fun onGlobalLayout() {
            viewTreeObserver.removeGlobalOnLayoutListener(this)
        }
    })
    return intArrayOf(width, height)
}
