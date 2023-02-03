package com.example.test2023app.utils

import android.view.View
import androidx.core.view.isVisible
import androidx.databinding.BindingAdapter

@BindingAdapter("isVisible")
fun View.setVisibility(isVisible: Boolean) {
    this.isVisible = isVisible
}