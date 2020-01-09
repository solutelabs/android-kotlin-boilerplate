package com.example.androidbasescaffoldapp.base

import android.annotation.SuppressLint
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import com.example.androidbasescaffoldapp.analytics.getAnalyticsLogger

@SuppressLint("Registered")
abstract class BaseActivity : AppCompatActivity() {

    abstract fun getScreenName(context: Context): String

    override fun onResume() {
        super.onResume()
        getAnalyticsLogger().setCurrentScreen(
            this@BaseActivity,
            getScreenName(this@BaseActivity)
        )
    }
}