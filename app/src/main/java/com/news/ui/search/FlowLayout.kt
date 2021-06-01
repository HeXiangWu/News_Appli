package com.news.ui.search

import android.content.Context
import android.content.res.Resources
import android.util.AttributeSet
import android.util.TypedValue
import android.view.View
import android.view.ViewGroup
import kotlin.math.max


class FlowLayout : ViewGroup {

    private var TAG =
        "/Users/wuhexiang/Desktop/News_App/app/src/main/java/com/news/ui/search/FlowLayout.kt"

    private val mHorizontalSpacing: Int = dp2px(16)

    private val mVerticalSpacing: Int = dp2px(8)

    // 记录所有的行，一行一行的存储，用于layout
    private var allLines: MutableList<MutableList<View>> = ArrayList()

    // 记录每一行的行高，用于layout
    private var lineHeights: MutableList<Int> = ArrayList()

    constructor(context: Context) : super(context) {

    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {

    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {

    }

    private fun clearMeasureParams() {
        allLines.clear()
        lineHeights.clear()
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        //  防止内存抖动
        clearMeasureParams()

        //  child
        var mChildCount: Int = childCount
        var mPaddingLeft: Int = paddingLeft

        // 获取测量大小（Size）
        var selfWidth: Int = MeasureSpec.getSize(widthMeasureSpec)
        var selfHeight: Int = MeasureSpec.getSize(heightMeasureSpec)

        //  本行已经用了多宽的size
        var lineWidthUsed: Int = 0
        //  本行的高度
        var lineHeight: Int = 0

        var lineViews: MutableList<View> = ArrayList()

        //  子View要求ViewGroup的高度
        var parentNeedHeight: Int = 0
        //  子View要求ViewGroup的宽度
        var parentNeedWidth: Int = 0



        for (i in 0 until childCount) {

            var childView: View = getChildAt(i)
            var childLP = childView.layoutParams

            if (childView.visibility != View.GONE) {
                //将layoutParams转变成为 measureSpec
                var childWidthMeasureSpec = getChildMeasureSpec(
                    widthMeasureSpec,
                    paddingLeft + paddingRight,
                    childLP.width
                )

                var childHeightMeasureSpec = getChildMeasureSpec(
                    heightMeasureSpec,
                    paddingTop + paddingBottom,
                    childLP.height
                )
                childView.measure(childWidthMeasureSpec, childHeightMeasureSpec)

                //  获取子View测量的宽高
                var childMeasureWidth: Int = childView.measuredWidth
                var childMeasureHeight: Int = childView.measuredHeight

                //  是否需要换行
                if (childMeasureWidth + mHorizontalSpacing + lineWidthUsed > selfWidth) {

                    allLines.add(lineViews)
                    lineHeights.add(lineHeight)

                    //  把本行的数据输入
                    parentNeedHeight += lineHeight + mVerticalSpacing
                    parentNeedWidth = max(parentNeedWidth, lineWidthUsed + mHorizontalSpacing)

                    //  本行数据重置
                    lineViews = ArrayList()
                    lineHeight = 0
                    lineWidthUsed = 0

                }
                lineViews.add(childView)
                lineWidthUsed += childMeasureWidth + mHorizontalSpacing
                lineHeight = max(lineHeight, childMeasureHeight)

                //  处理最后一行的数据
                if (i == mChildCount - 1) {
                    allLines.add(lineViews)
                    lineHeights.add(lineHeight)
                    parentNeedHeight += lineHeight + mVerticalSpacing
                    parentNeedWidth = max(parentNeedWidth, lineWidthUsed + mHorizontalSpacing)
                }
            }
        }

        var widthMode: Int = MeasureSpec.getMode(widthMeasureSpec)
        var heightMode: Int = MeasureSpec.getMode(heightMeasureSpec)

        var realWidth: Int = if (widthMode == MeasureSpec.EXACTLY) selfWidth else parentNeedWidth

        var realHeight: Int =
            if (heightMode == MeasureSpec.EXACTLY) selfHeight else parentNeedHeight

        setMeasuredDimension(realWidth, realHeight)

    }


    override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int) {

        var curL: Int = paddingLeft
        var curT: Int = paddingTop

        for (i in 0 until allLines.size) {

            var curList: MutableList<View> = allLines[i]
            var lineHeight: Int = lineHeights[i]

            for (j in 0 until curList.size) {
                var curView: View = curList[j]
                var left = curL
                var top = curT
                var right = left + curView.measuredWidth
                var bottom = top + curView.measuredHeight

                curView.layout(left, top, right, bottom)
                curL = right + mHorizontalSpacing

            }
            curL = paddingLeft
            curT += lineHeight + mVerticalSpacing

        }

    }

    private fun dp2px(dp: Int): Int {
        return TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP,
            dp.toFloat(),
            Resources.getSystem().displayMetrics
        ).toInt()
    }

}