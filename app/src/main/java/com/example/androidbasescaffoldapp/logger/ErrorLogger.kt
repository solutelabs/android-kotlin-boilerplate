package com.example.androidbasescaffoldapp.logger

import android.app.Application

interface ErrorLogger {
    suspend fun init(application: Application)
    fun logEvent(exception: Exception)
}