package com.example.komalkotlin2024.dagger

import android.provider.CallLog
import android.provider.ContactsContract.CommonDataKinds.Callable
import android.telecom.Call
import com.example.komalkotlin2024.mvvm.model.Post
import com.example.komalkotlin2024.mvvm.model.User
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.mock
import org.mockito.Mockito.`when`
import org.mockito.internal.verification.Calls
import org.mockito.junit.MockitoJUnitRunner
import retrofit2.Callback

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class PostViewModelTest {


    private lateinit var viewModel: PostViewModel
    @Mock
    private lateinit var postApiInterface: PostApiInterface


    @Before
    fun setUp(){
        viewModel = PostViewModel()
    }

    @Test
    fun getPostsTest(){
        val posts = listOf(Post(1, 2, "post name", "body"))
      //  val response: Calls()
        Mockito.`when`(PostApiClient.providesPostApiInterface()).thenReturn(postApiInterface)
       // `when`(postApiInterface.getPosts()).thenReturn()

    }
}