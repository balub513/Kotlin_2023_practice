package com.example.test2023app.utils

import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

fun LifecycleOwner.safeLaunch(block: suspend CoroutineScope.() -> Unit) {
    lifecycleScope.launch {
        try {
            block()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}