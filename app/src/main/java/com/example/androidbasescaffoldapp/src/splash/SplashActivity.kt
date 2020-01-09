package com.example.androidbasescaffoldapp.src.splash

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.lifecycleScope
import com.example.androidbasescaffoldapp.MainActivity
import com.example.androidbasescaffoldapp.base.BaseActivity
import kotlinx.coroutines.launch

class SplashActivity : BaseActivity() {
    override fun getScreenName(context: Context): String = "Splash Screen"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val viewModel = ViewModelProviders.of(this)[SplashViewModel::class.java]
        lifecycleScope.launch {
            viewModel.initServices()
            viewModel.moveToNextScreen.observe(this@SplashActivity, Observer {
                if (it) {
                    startActivity(Intent(this@SplashActivity, MainActivity::class.java))
                    finish()
                }
            })
        }
    }
}