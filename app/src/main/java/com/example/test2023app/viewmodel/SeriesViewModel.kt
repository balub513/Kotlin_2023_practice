package com.example.test2023app.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.test2023app.model.NetworkResult
import com.example.test2023app.model.response.serieses.Series
import com.example.test2023app.repository.CricRepo
import com.example.test2023app.utils.safeLaunch
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class SeriesViewModel @Inject constructor(
    private val repo: CricRepo
) : BaseViewModel() {

    companion object {
        private const val TAG: String = "CricketMatchesViewModel"
    }

    var responseSeries: MutableStateFlow<NetworkResult<Response<Series>>?> =
        MutableStateFlow(null)
    var seriesLiveData: MutableLiveData<NetworkResult<Series>?> =
        MutableLiveData()

    fun series() {
        Log.d(TAG, "seriesInfo sealed")
        responseSeries.value = NetworkResult.Loading()
        safeLaunch {
            val response = repo.series()
            when {
                response.isSuccessful -> {
                    val success = NetworkResult.Success(response)
                    responseSeries.value = success
                }
                else -> {
                    responseSeries.value = NetworkResult.Error("", null)
                }
            }
        }
    }

    fun series1() {
        Log.d(TAG, "seriesInfo sealed")
        seriesLiveData.value = NetworkResult.Loading()
        safeLaunch {
            val response = repo.series1()
            if (response.data.size > 0) {
                seriesLiveData.value = NetworkResult.Success(response)
            } else {
                seriesLiveData.value = NetworkResult.Error("", null)
            }

        }
    }
}