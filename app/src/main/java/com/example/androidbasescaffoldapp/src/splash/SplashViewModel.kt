package com.example.androidbasescaffoldapp.src.splash

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.androidbasescaffoldapp.remote_config.RemoteConfigRepository
import kotlinx.coroutines.launch

class SplashViewModel : ViewModel() {

    val moveToNextScreen = MutableLiveData<Boolean>(false)

    suspend fun initServices() {
        viewModelScope.launch {
            RemoteConfigRepository().init()
            moveToNextScreen.value = true
        }
    }
}