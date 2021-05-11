package com.example.myapp.util

import android.widget.Toast
import com.example.myapp.MyApplication

fun String.showToast(){
    Toast.makeText(
        MyApplication.context,
        this,
        Toast.LENGTH_SHORT
    ).show()
}