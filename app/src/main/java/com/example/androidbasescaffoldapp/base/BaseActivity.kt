package com.example.androidbasescaffoldapp.base

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import com.example.androidbasescaffoldapp.analytics.getAnalyticsLogger
import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.Deferred

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

    data class ActivityResult(
        val resultCode: Int,
        val data: Intent?
    )

    var currentCode: Int = 0
    private val resultByCode = mutableMapOf<Int, CompletableDeferred<ActivityResult?>>()

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        resultByCode[requestCode]?.let {
            it.complete(ActivityResult(resultCode, data))
            resultByCode.remove(requestCode)
        } ?: run {
            super.onActivityResult(requestCode, resultCode, data)
        }
    }

    fun launchIntent(intent: Intent): Deferred<ActivityResult?> {
        val activityResult = CompletableDeferred<ActivityResult?>()

        if (intent.resolveActivity(packageManager) != null) {
            val resultCode = currentCode++
            resultByCode[resultCode] = activityResult
            startActivityForResult(intent, resultCode)
        } else {
            activityResult.complete(null)
        }
        return activityResult
    }

}