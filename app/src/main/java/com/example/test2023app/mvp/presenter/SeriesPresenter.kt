package com.example.test2023app.mvp.presenter

import com.example.test2023app.mvp.contract.SeriesInfoContract
import com.example.test2023app.repository.CricRepo
import kotlinx.coroutines.*
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

class SeriesPresenter @Inject constructor(
    private val view: SeriesInfoContract.View, private val repo: CricRepo,
) : SeriesInfoContract.Presenter, CoroutineScope {

    private val job = Job()
    override val coroutineContext: CoroutineContext = job + Dispatchers.IO

    override fun getSeries() {
        launch(Dispatchers.IO) {
            val response = repo.series()
            withContext(Dispatchers.Main) {
                if (response.isSuccessful)
                    response.body()?.let { view.successSeriesInfo(it) }
                else
                    view.failureSeriesInfo()
            }
        }

    }


}