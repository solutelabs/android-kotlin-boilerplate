package com.example.androidbasescaffoldapp.base

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.example.androidbasescaffoldapp.analytics.getAnalyticsLogger
import kotlinx.coroutines.CompletableDeferred

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

    private var currentCode: Int = 0
    private val resultByCode = mutableMapOf<Int, CompletableDeferred<ActivityResult?>>()

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        resultByCode[requestCode]?.let {
            it.complete(ActivityResult(resultCode, data))
            resultByCode.remove(requestCode)
        } ?: run {
            super.onActivityResult(requestCode, resultCode, data)
        }
    }

    suspend fun launchIntent(intent: Intent): ActivityResult? {
        val activityResult = CompletableDeferred<ActivityResult?>()

        if (intent.resolveActivity(packageManager) != null) {
            val resultCode = currentCode++
            resultByCode[resultCode] = activityResult
            startActivityForResult(intent, resultCode)
        } else {
            activityResult.complete(null)
        }
        return activityResult.await()
    }

    data class PermissionResult(
        val permissions: Array<out String>,
        val grantResult: IntArray
    ) {
        override fun equals(other: Any?): Boolean {
            if (this === other) return true
            if (javaClass != other?.javaClass) return false

            other as PermissionResult

            if (!permissions.contentEquals(other.permissions)) return false
            if (!grantResult.contentEquals(other.grantResult)) return false

            return true
        }

        override fun hashCode(): Int {
            var result = permissions.contentHashCode()
            result = 31 * result + grantResult.contentHashCode()
            return result
        }
    }

    private var permissionCode: Int = 0
    private val permissionResultByCode = mutableMapOf<Int, CompletableDeferred<PermissionResult?>>()

    suspend fun askPermission(permissions: Array<String>): PermissionResult? {
        val permissionResult = CompletableDeferred<PermissionResult?>()
        val resultCode = permissionCode++
        permissionResultByCode[resultCode] = permissionResult
        ActivityCompat.requestPermissions(this, permissions, resultCode)
        return permissionResult.await()
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        permissionResultByCode[requestCode]?.let {
            it.complete(PermissionResult(permissions, grantResults))
            permissionResultByCode.remove(requestCode)
        } ?: run {
            super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        }
    }


}