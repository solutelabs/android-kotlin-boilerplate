package com.example.androidbasescaffoldapp.utils

import com.google.android.gms.tasks.Task
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

suspend fun <T> Task<T>.suspendTask(): T? = suspendCoroutine { cont ->
    this.addOnCompleteListener {
        if (it.isSuccessful) {
            cont.resume(it.result)
        } else {
            cont.resume(null)
        }
    }
}
