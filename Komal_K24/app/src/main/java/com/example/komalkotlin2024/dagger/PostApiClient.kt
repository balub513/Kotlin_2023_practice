package com.example.komalkotlin2024.dagger

import com.example.komalkotlin2024.Constants
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

@Module
object PostApiClient {

   // @Provides
    public fun providesPostRetrofit(/*okHttpClient: OkHttpClient*/): Retrofit{
        return Retrofit.Builder().baseUrl(Constants.BASE_URL).client(providesOkHttpClient(
            providesInterceptor()
        )).addConverterFactory(GsonConverterFactory.create(GsonBuilder().create())).addCallAdapterFactory(
            RxJava2CallAdapterFactory.create()).build()

    }
   // @Provides
    public fun providesOkHttpClient(interceptor: Interceptor): OkHttpClient {

        return OkHttpClient.Builder().addInterceptor(interceptor).build()

    }

   // @Provides
    public fun providesInterceptor(): Interceptor {
        return  HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
    }

   // @Provides
    fun providesPostApiInterface(): PostApiInterface {
        //return providesPostRetrofit().create(PostApiInterface::class.java)
        val  interceptor =  HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        val okHttpClient =  OkHttpClient.Builder().addInterceptor(interceptor).build();

        val postApiInterface =  Retrofit.Builder().baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build().create(PostApiInterface::class.java)
        return postApiInterface;
    }
}