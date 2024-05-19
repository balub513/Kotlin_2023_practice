package com.example.komalkotlin2024

import android.app.Application
import com.example.komalkotlin2024.dagger.DIComponent
import com.example.komalkotlin2024.dagger.DaggerDIComponent

class KomalApplication: Application() {


    override fun onCreate() {
        super.onCreate()


    }
    companion object
    {
        private val diComponent = DaggerDIComponent.create()

        fun getDiComponent(){
          //  return diComponent
        }
    }
}