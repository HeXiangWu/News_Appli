package com.news

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context

class NewsApplication : Application() {

    companion object {
        @SuppressLint("StaticFieldLeak")
        lateinit var context: Context

        // 聚合数据 新闻头条API的口令
        const val KEY = "dd6e8ee5b5a00994663faea6b673f8ad"
        val newsTypeChineseName = mapOf( "top" to "头条",
            "shehui" to "社会", "guonei" to "国内", "guoji" to "国际",
            "yule" to "娱乐", "tiyu" to "体育", "junshi" to "军事",
            "keji" to "科技", "caijing" to "财经", "shishang" to "时尚"
        )
    }

    override fun onCreate() {
        super.onCreate()
        context = baseContext
    }
}