package com.example.test2023app.model

sealed class NetworkResult<T>(
    val data: T? = null,
    val message: String? = null
) {

    data class Success<T>(val data1: T) : NetworkResult<T>(data1)

    class Error<T>(message: String?, data: T? = null) : NetworkResult<T>(data, message)

    class Loading<T> : NetworkResult<T>()

}