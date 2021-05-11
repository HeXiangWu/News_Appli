package com.example.myapp

import android.app.Application
import android.content.Context

class MyApplication:Application(){
    companion object{
        lateinit var context:Context
        const val KEY = "dd6e8ee5b5a00994663faea6b673f8ad"
    }

    override fun onCreate() {
        super.onCreate()
        context = baseContext
    }
}