package com.example.komalkotlin2024.dagger

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.komalkotlin2024.mvvm.model.Post
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


public class PostViewModel  ():ViewModel() {



    private var postsLivedata = MutableLiveData<List<Post>>()

    val posts = postsLivedata

//    @Inject
//    lateinit var postApiInterface: PostApiInterface


    fun getPosts(){
        viewModelScope.launch {
            val postList: Call<List<Post>> = PostApiClient.providesPostApiInterface().getPosts()
            postList.enqueue(object : Callback<List<Post>>{
                override fun onResponse(call: Call<List<Post>>, response: Response<List<Post>>) {
                    posts.postValue(response.body())
                }

                override fun onFailure(call: Call<List<Post>>, t: Throwable) {
                }

            })

        }
    }
}