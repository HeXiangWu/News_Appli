package com.news.ui

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.graphics.BitmapFactory
import android.os.Build
import android.os.Build.VERSION
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import androidx.lifecycle.LiveData
import androidx.navigation.NavController
import com.news.R
import com.news.databinding.ActivityMainBinding
import com.news.util.LogUtil
import com.news.util.setupWithNavController
import com.news.util.showToast



class MainActivity : AppCompatActivity() {

    private var currentNavController: LiveData<NavController>? = null

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        //通知


        LogUtil.e(TAG, "onCreate: ")
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)


        if (savedInstanceState == null) {
            setupBottomNavigationBar()
        }
        // if savedInstanceState != null 则由onRestoreInstanceState方法恢复页面的状态
    }

    // onRestoreInstanceState(Bundle savedInstanceState)只有在activity确实是被系统回收，重新创建activity的情况下才会被调用
    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        LogUtil.e(TAG, "onRestoreInstanceState: ")
        super.onRestoreInstanceState(savedInstanceState)
        setupBottomNavigationBar()
    }

    override fun onSupportNavigateUp(): Boolean {
        LogUtil.e(TAG, "onSupportNavigateUp: ")
        return currentNavController?.value?.navigateUp() ?: false
    }

    private fun setupBottomNavigationBar() {
        val navGraphIds = listOf(R.navigation.home, R.navigation.video, R.navigation.user)
        val controller = binding.bottomNavigationView.setupWithNavController(
            navGraphIds = navGraphIds,
            fragmentManager = supportFragmentManager,
            containerId = R.id.nav_host_container,
            intent = intent
        )
        currentNavController = controller
    }

    companion object {
        const val TAG = "MainActivity"
    }


}