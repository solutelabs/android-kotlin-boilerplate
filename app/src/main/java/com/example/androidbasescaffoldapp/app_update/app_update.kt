package com.example.androidbasescaffoldapp.app_update

import android.app.Activity
import com.example.androidbasescaffoldapp.BuildConfig
import com.example.androidbasescaffoldapp.R
import com.example.androidbasescaffoldapp.remote_config.RemoteConfigRepository
import com.example.androidbasescaffoldapp.utils.openUrl
import com.example.androidbasescaffoldapp.utils.showAlertDialog
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

enum class UpdateMode { NoUpdate, FlexibleUpdate, ImmediateUpdate }

const val appUpdateURL = "http://www.google.com"

suspend fun checkForAppUpdate(
    activity: Activity,
    remoteConfigRepo: RemoteConfigRepository
): Boolean {
    return suspendCoroutine { continuation ->
        val update = availableUpdate(remoteConfigRepo)

        if (update == UpdateMode.NoUpdate) {
            continuation.resume(false)
        }

        if (update == UpdateMode.FlexibleUpdate) {
            activity.showAlertDialog(
                title = activity.getString(R.string.title_immediate_update),
                content = activity.getString(R.string.content_immediate_update),
                positiveButtonText = activity.getString(R.string.update_alert_btn_positive),
                negativeButtonText = activity.getString(R.string.update_alert_btn_negative),
                onClickPositiveButton = {
                    activity.openUrl(appUpdateURL)
                    continuation.resume(true)
                },
                onClickNegativeButton = {
                    continuation.resume(false)
                },
                onCancelListener = {
                    continuation.resume(false)
                }
            )
        }

        if (update == UpdateMode.ImmediateUpdate) {
            activity.showAlertDialog(
                title = activity.getString(R.string.title_immediate_update),
                content = activity.getString(R.string.content_immediate_update),
                positiveButtonText = activity.getString(R.string.update_alert_btn_positive),
                isCancelable = false,
                onClickPositiveButton = {
                    activity.openUrl(appUpdateURL)
                    continuation.resume(true)
                }
            )
        }

    }
}

private fun availableUpdate(remoteConfigRepo: RemoteConfigRepository): UpdateMode {
    val currentVersion = BuildConfig.VERSION_CODE
    val latestStableVersion = remoteConfigRepo["android_latest_stable_version"].asLong().toInt()
    val latestVersion = remoteConfigRepo["android_latest_version"].asLong().toInt()


    if (currentVersion >= latestVersion) {
        return UpdateMode.NoUpdate
    }

    if (currentVersion < latestStableVersion) {
        return UpdateMode.ImmediateUpdate
    }

    return UpdateMode.FlexibleUpdate
}

