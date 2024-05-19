package com.example.komalkotlin2024.dagger

import com.example.komalkotlin2024.mvvm.model.Post
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface PostApiInterface {

    //removed suspend keyword while using call
    @GET("posts")
    public fun getPosts(): Call<List<Post>>

    @GET("posts/{id}")
    suspend fun getPost(@Path("id") userId: Int): Call<Post>
}