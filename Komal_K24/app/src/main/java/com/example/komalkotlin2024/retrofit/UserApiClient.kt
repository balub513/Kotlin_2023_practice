package com.example.komalkotlin2024.retrofit

import com.example.komalkotlin2024.Constants
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object UserApiClient {

    fun providesRetrofit(): Retrofit{
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(providesOkHttpClient())
            .build()
    }

    fun providesOkHttpClient(): OkHttpClient{

       return OkHttpClient.Builder().addInterceptor(providesInterceptor()).build()

    }

    fun providesInterceptor(): Interceptor{
      return  HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
    }

    fun provideUserApiInterface(): UserApiInterface{
        return providesRetrofit().create(UserApiInterface::class.java)
    }


}