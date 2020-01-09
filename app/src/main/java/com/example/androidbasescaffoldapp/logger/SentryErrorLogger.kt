package com.example.androidbasescaffoldapp.logger

import android.app.Application
import io.sentry.Sentry
import io.sentry.android.AndroidSentryClientFactory

object SentryErrorLogger : ErrorLogger {

    override suspend fun init(application: Application) {
        //TODO Add Sentry DSN here & uncomment
//        Sentry.init(
//            "DSN",
//            AndroidSentryClientFactory(application)
//        )
    }

    override fun logEvent(exception: Exception) {
        Sentry.capture(exception)
    }

}

fun getErrorLogger(): ErrorLogger = SentryErrorLogger