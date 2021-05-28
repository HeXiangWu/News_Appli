package com.news.ui.user

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.news.databinding.UserNavigationViewBinding


class UserNavigationView : AppCompatActivity() {
    companion object {
        @JvmStatic
        fun actionStart(context: Context) {
            val intent = Intent(context, UserNavigationView::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            context.startActivity(intent)
        }
    }
    private lateinit var binding: UserNavigationViewBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = UserNavigationViewBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
    }
}