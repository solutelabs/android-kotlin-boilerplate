package com.example.androidbasescaffoldapp.src.splash

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.androidbasescaffoldapp.MainActivity
import com.example.androidbasescaffoldapp.app_update.checkForAppUpdate
import com.example.androidbasescaffoldapp.base.BaseActivity
import com.example.androidbasescaffoldapp.remote_config.RemoteConfigRepository
import kotlinx.coroutines.launch

class SplashActivity : BaseActivity() {
    override fun getScreenName(context: Context): String = "Splash Screen"

    private val viewModel by viewModels<SplashViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        lifecycleScope.launch {
            viewModel.initServices()
            val isUpdateStarted = checkForAppUpdate(
                activity = this@SplashActivity,
                remoteConfigRepo = RemoteConfigRepository()
            )
            if (!isUpdateStarted) {
                startActivity(Intent(this@SplashActivity, MainActivity::class.java))
            }
            finish()
        }
    }
}