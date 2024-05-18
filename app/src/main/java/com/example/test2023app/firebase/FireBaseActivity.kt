package com.example.test2023app.firebase

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.test2023app.R
import com.google.firebase.messaging.FirebaseMessaging

class FireBaseActivity : AppCompatActivity() {
    private val TAG = "FireBaseActivity"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fire_base)

        FirebaseMessaging.getInstance().token.addOnCompleteListener {
            if (it.isComplete) {
                var firebaseToken = it.result.toString()
                Log.d(TAG, firebaseToken)
            }
        }

    }
}