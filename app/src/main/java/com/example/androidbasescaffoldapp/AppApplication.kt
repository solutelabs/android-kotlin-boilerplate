package com.example.androidbasescaffoldapp

import android.app.Application
import com.example.androidbasescaffoldapp.analytics.getAnalyticsLogger
import com.example.androidbasescaffoldapp.logger.getErrorLogger
import kotlinx.coroutines.runBlocking

class AppApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        runBlocking {
            if (!BuildConfig.DEBUG) {
                getErrorLogger().init(this@AppApplication)
                getAnalyticsLogger().init(this@AppApplication)
            }
        }
    }
}