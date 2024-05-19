package com.example.komalkotlin2024.dagger

import com.example.komalkotlin2024.mvvm.PostsFragment
import dagger.Component

@Component(modules = [PostApiClient::class])
interface DIComponent {
    //fun inject(postsFragment: PostsFragment)

    fun inject(postViewModel: PostViewModel)

}
