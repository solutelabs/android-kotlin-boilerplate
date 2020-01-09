package com.example.androidbasescaffoldapp

import android.content.Context
import android.os.Bundle
import com.example.androidbasescaffoldapp.base.BaseActivity

class MainActivity : BaseActivity() {
    override fun getScreenName(context: Context): String = "Hello World Screen (Base)"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}
