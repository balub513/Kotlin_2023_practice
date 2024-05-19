package com.example.komalkotlin2024.retrofit

import com.example.komalkotlin2024.mvvm.model.Album
import com.example.komalkotlin2024.mvvm.model.Comment
import com.example.komalkotlin2024.mvvm.model.Photo
import com.example.komalkotlin2024.mvvm.model.Post
import com.example.komalkotlin2024.mvvm.model.User
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface UserApiInterface {

    @GET("users")
    suspend fun getUsers(): List<User>

    @GET("comments")
    suspend fun  getComments(): Response<List<Comment>>

    @GET("comments/{id}")
    suspend fun getComment(@Path("id")postId: Int): Response<Comment>

    @GET("albums")
    suspend fun getAlbums(): Response<List<Album>>

    @GET("albums/{id}")
    suspend fun getAlbum(@Path("id") userId: Int): Response<Album>

    @GET("photos")
    suspend fun getPhotos(): Response<List<Photo>>

    @GET("photos/{id}")
    suspend fun getPhoto(@Path("id") albumId: Int): Response<Photo>


}