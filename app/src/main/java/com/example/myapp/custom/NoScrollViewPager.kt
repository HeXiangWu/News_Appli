package com.example.myapp.custom

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import androidx.viewpager.widget.ViewPager

class NoScrollViewPager:ViewPager {
    constructor(context:Context):super(context)

    constructor(context: Context,attrs:AttributeSet):super(context,attrs)

    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {
        return false
    }

    override fun onInterceptTouchEvent(ev: MotionEvent?): Boolean {
        return false
    }
}