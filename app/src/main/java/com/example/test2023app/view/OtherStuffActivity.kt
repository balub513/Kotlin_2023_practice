package com.example.test2023app.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.test2023app.R
import com.example.test2023app.other.Sample
import com.example.test2023app.other.callBackListener
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

class OtherStuffActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_other_stuff)
    }


    suspend fun testCallBacksApiWithCoroutines(){
        suspendCoroutine {
            continuation ->
            Sample.doWork(7,object : callBackListener {
                override fun onSuccess(msg: String) {
                    continuation.resume(msg)
                }


                override fun onFailure(throwable: Throwable) {
                    continuation.resumeWithException(throwable)
                }

            })
        }

    }
}