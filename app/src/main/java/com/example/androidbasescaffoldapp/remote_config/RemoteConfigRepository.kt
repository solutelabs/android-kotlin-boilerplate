package com.example.androidbasescaffoldapp.remote_config

import com.example.androidbasescaffoldapp.utils.suspendTask
import com.google.firebase.ktx.Firebase
import com.google.firebase.remoteconfig.ktx.get
import com.google.firebase.remoteconfig.ktx.remoteConfig
import com.google.firebase.remoteconfig.ktx.remoteConfigSettings

class RemoteConfigRepository {
    suspend fun init() {
        val remoteConfig = Firebase.remoteConfig
        val configSettings = remoteConfigSettings {
            minimumFetchIntervalInSeconds = 12
        }
        remoteConfig.setConfigSettingsAsync(configSettings)
        remoteConfig.fetchAndActivate().suspendTask()
    }

    operator fun get(key: String) = Firebase.remoteConfig[key]
}


