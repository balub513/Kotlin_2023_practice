package com.example.test2023app.di

import android.app.Activity
import com.example.test2023app.contract.SeriesInfoContract
import com.example.test2023app.presenter.SeriesPresenter
import com.example.test2023app.view.SeriesActivity
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

@InstallIn(ActivityComponent::class)
@Module
abstract class MainModule {

    @Binds
    abstract fun bindActivity(activity: SeriesActivity): SeriesInfoContract.View

    @Binds
    abstract fun bindSeriesInfoPresenter(presenter: SeriesPresenter): SeriesInfoContract.Presenter
}

@InstallIn(ActivityComponent::class)
@Module
object MainActivityModule {

    @Provides
    fun bindActivity(activity: Activity): SeriesActivity {
        return activity as SeriesActivity
    }
}