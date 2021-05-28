package com.news.service

import android.app.IntentService
import android.content.Intent
import android.util.Log


@Suppress("UNREACHABLE_CODE")
class MyIntentService : IntentService("MyIntentService"){
    override fun onHandleIntent(intent: Intent?) {
        Log.d("MyIntentService","Thread id is ${Thread.currentThread().name}")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("MyIntentService","onDestroy executed")
    }
}