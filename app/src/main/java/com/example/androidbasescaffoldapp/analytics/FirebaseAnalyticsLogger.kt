package com.example.androidbasescaffoldapp.analytics

import android.app.Activity
import android.content.Context
import android.os.Bundle
import com.google.firebase.analytics.FirebaseAnalytics

object FirebaseAnalyticsLogger : AnalyticsLogger {

    private var firebaseAnalytics: FirebaseAnalytics? = null

    override fun init(context: Context) {
        firebaseAnalytics = FirebaseAnalytics.getInstance(context)
    }

    override fun loginUser(userData: Map<String, Any>) {
        firebaseAnalytics?.logEvent("Login", null)
        firebaseAnalytics?.setUserId("${userData["id"]}")
        firebaseAnalytics?.setUserProperty("email", "${userData["email"]}")
    }

    override fun logoutUser() {
        firebaseAnalytics?.logEvent("Logout", null)
        firebaseAnalytics?.setUserId(null)
        firebaseAnalytics?.setUserProperty("email", null)
    }

    override fun logEvent(eventName: String, params: Map<String, Any>) {
        val bundle = Bundle()
        params.forEach {
            bundle.putString(it.key, "${it.value}")
        }
        firebaseAnalytics?.logEvent(eventName, bundle)
    }

    override fun setCurrentScreen(activity: Activity, screenName: String) {
        val bundle = Bundle()
        bundle.putString(FirebaseAnalytics.Param.SCREEN_NAME, screenName)
        firebaseAnalytics?.logEvent(FirebaseAnalytics.Event.SCREEN_VIEW, bundle);
    }
}

fun getAnalyticsLogger(): AnalyticsLogger = FirebaseAnalyticsLogger