package com.example.androidbasescaffoldapp

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.lifecycleScope
import com.example.androidbasescaffoldapp.base.BaseActivity
import com.example.androidbasescaffoldapp.remote_config.RemoteConfigRepository
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {
    override fun getScreenName(context: Context): String = "Hello World Screen (Base)"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        textMain.text = RemoteConfigRepository()["welcome_msg"].asString()
        lifecycleScope.launchWhenCreated {
            val permission =
                askPermission(arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE))
            permission?.let {
                Log.d(
                    "PERMISSION_RESULT",
                    "${permission.grantResult[0] == PackageManager.PERMISSION_GRANTED}"
                )
            }
        }
    }
}
