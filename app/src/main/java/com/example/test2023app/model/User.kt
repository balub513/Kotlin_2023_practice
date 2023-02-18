package com.example.test2023app.model

import androidx.databinding.BaseObservable
import androidx.databinding.ObservableField

data class User(
    var username: ObservableField<String> = ObservableField<String>(),
    var email: ObservableField<String> = ObservableField<String>(),
    var password: ObservableField<String> = ObservableField<String>(),
    var gender: ObservableField<String> = ObservableField<String>(),
    var nationality: ObservableField<String> = ObservableField<String>(),
) : BaseObservable()

