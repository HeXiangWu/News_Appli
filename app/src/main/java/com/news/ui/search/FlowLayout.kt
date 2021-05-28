package com.news.ui.search

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.view.ViewGroup

class FlowLayout(context: Context, attrs: AttributeSet, defStyle: Int) :
    ViewGroup(context, attrs, defStyle) {

    private val TAG = "FlowLayout"
    private val LEFT = -1
    private val CENTER = 0
    private val RIGHT = 1

    private val limitLineCount //默认显示3行 断词条显示3行，长词条显示2行
            = 0
    private var isLimit //是否有行限制
            = false
    private var isOverFlow //是否溢出2行
            = false

    private val mGravity = 0
    protected var mAllViews: List<List<View>> = ArrayList<List<View>>()
    protected var mLineHeight: List<Int> = ArrayList()
    protected var mLineWidth: List<Int> = ArrayList()
    private val lineViews: List<View> = ArrayList()

    fun isOverFlow(): Boolean {
        return isOverFlow
    }

    private fun setOverFlow(overFlow: Boolean) {
        isOverFlow = overFlow
    }

    fun isLimit(): Boolean {
        return isLimit
    }

    fun setLimit(limit: Boolean) {
        if (!limit) {
            setOverFlow(false)
        }
        isLimit = limit
    }

    override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int) {
        TODO("Not yet implemented")
    }

    override fun generateLayoutParams(attrs: AttributeSet?): LayoutParams? {
        return MarginLayoutParams(context, attrs)
    }

    override fun generateDefaultLayoutParams(): LayoutParams? {
        return MarginLayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT)
    }

    override fun generateLayoutParams(p: LayoutParams?): LayoutParams? {
        return MarginLayoutParams(p)
    }
}