package com.example.test2023app.presenter

import androidx.lifecycle.LifecycleCoroutineScope
import com.example.test2023app.contract.SeriesInfoContract
import com.example.test2023app.repo.CricRepo
import kotlinx.coroutines.*
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

class SeriesInfoPresenter @Inject constructor(
    private val view: SeriesInfoContract.View, private val repo: CricRepo,
) : SeriesInfoContract.Presenter, CoroutineScope {

    private val job = Job()
    override val coroutineContext: CoroutineContext = job + Dispatchers.IO

    override fun getSeriesInfoData() {
        launch(Dispatchers.IO) {
            val response = repo.seriesInfo()
            withContext(Dispatchers.Main) {
                if (response.isSuccessful)
                    response.body()?.let { view.successSeriesInfo(it) }
                else
                    view.failureSeriesInfo()
            }
        }

    }


}