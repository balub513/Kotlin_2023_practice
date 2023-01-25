package com.example.test2023app.di

import android.app.Activity
import com.example.test2023app.contract.SeriesInfoContract
import com.example.test2023app.presenter.SeriesInfoPresenter
import com.example.test2023app.view.SeriesInfoActivity
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.components.SingletonComponent

@InstallIn(ActivityComponent::class)
@Module
abstract class MainModule {

    @Binds
    abstract fun bindActivity(activity: SeriesInfoActivity): SeriesInfoContract.View

    @Binds
    abstract fun bindSeriesInfoPresenter(presenter: SeriesInfoPresenter): SeriesInfoContract.Presenter
}

@InstallIn(ActivityComponent::class)
@Module
object MainActivityModule {

    @Provides
    fun bindActivity(activity: Activity): SeriesInfoActivity {
        return activity as SeriesInfoActivity
    }
}