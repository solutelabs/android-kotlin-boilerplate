package com.example.androidbasescaffoldapp

import android.content.Context
import android.os.Bundle
import com.example.androidbasescaffoldapp.base.BaseActivity
import com.example.androidbasescaffoldapp.remote_config.RemoteConfigRepository
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {
    override fun getScreenName(context: Context): String = "Hello World Screen (Base)"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        textMain.text = RemoteConfigRepository()["welcome_msg"].asString()
    }
}
