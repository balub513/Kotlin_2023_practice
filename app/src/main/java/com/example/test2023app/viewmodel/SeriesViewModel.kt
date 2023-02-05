package com.example.test2023app.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.test2023app.model.NetworkResult
import com.example.test2023app.model.response.serieses.Series
import com.example.test2023app.repository.CricRepo
import com.example.test2023app.utils.safeLaunch
import dagger.hilt.android.lifecycle.HiltViewModel
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class SeriesViewModel @Inject constructor(
    private val repo: CricRepo,
    application: Application
) : BaseViewModel(application) {

    companion object {
        private const val TAG: String = "CricketMatchesViewModel"
    }

    var responseSeries: MutableLiveData<NetworkResult<Response<Series>>> =
        MutableLiveData<NetworkResult<Response<Series>>>()

    fun series() {
        Log.d(TAG,"seriesInfo sealed")
        responseSeries.value = NetworkResult.Loading()
        safeLaunch {
            val response = repo.series()
            when {
                response.isSuccessful -> {
                    val success = NetworkResult.Success(response)
                    responseSeries.postValue(success)
                }
                else -> {
                    NetworkResult.Error("message = ", null)
                }
            }
        }
    }
}