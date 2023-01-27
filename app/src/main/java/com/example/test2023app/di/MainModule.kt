package com.example.test2023app.di

import android.app.Activity
import com.example.test2023app.mvp.contract.SeriesInfoContract
import com.example.test2023app.mvp.presenter.SeriesPresenter
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
    abstract fun bindSeriesActivity(activity: SeriesActivity): SeriesInfoContract.View

//    @Provides
//    fun getSeriesInfoContractView(activity: SeriesActivity): SeriesInfoContract.View {
//        return activity;
//    }

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