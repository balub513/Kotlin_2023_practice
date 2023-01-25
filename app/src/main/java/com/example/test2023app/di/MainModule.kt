package com.example.test2023app.di

import com.example.test2023app.contract.SeriesInfoContract
import com.example.test2023app.presenter.SeriesInfoPresenter
import com.example.test2023app.view.SeriesInfoActivity
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
abstract class MainModule {

    @Binds
    abstract fun bindActivity(activity: SeriesInfoActivity): SeriesInfoContract.View

    @Binds
    abstract fun bindSeriesInfoPresenter(presenter: SeriesInfoPresenter): SeriesInfoContract.Presenter
}