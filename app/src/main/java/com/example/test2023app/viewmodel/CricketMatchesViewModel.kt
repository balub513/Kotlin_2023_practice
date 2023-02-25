package com.example.test2023app.viewmodel


import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.*
import com.example.moduelcreation.ModuleSingleton
import com.example.test2023app.model.response.current_matches.CurrentMatches
import com.example.test2023app.repository.CricRepo
import com.example.test2023app.utils.CUtils
import com.example.test2023app.utils.safeLaunch
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.SingleObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject
import kotlinx.coroutines.channels.Channel
import javax.inject.Inject

@HiltViewModel
class CricketMatchesViewModel @Inject constructor(
    private val repo: CricRepo
) : BaseViewModel() {

    companion object {
        private const val TAG: String = "CricketMatchesViewModel"
    }

    private val _currentMatches = MutableLiveData<CurrentMatches>()
    val currentMatches: LiveData<CurrentMatches> = _currentMatches

    private val _currentMatchesFailed = MutableLiveData<String>()
    val currentMatchesFailed: LiveData<String> = _currentMatchesFailed

    private val _navigateToSeriesInfoChannel = Channel<String>(Channel.CONFLATED)
    val navigateToSeriesInfoChannel = _navigateToSeriesInfoChannel


    fun currentMatches() {
        val currentTime = ModuleSingleton.getCurrentTime()

        safeLaunch {
            val currentMatches = repo.currentMatches()
            if (currentMatches.isSuccessful) {
                _currentMatches.postValue(currentMatches.body())
                _navigateToSeriesInfoChannel.send(CUtils.CHANNEL_NAVIGATE_TO_SERIES_INFO)
            } else
                _currentMatchesFailed.postValue("CurrentMatches API failed")
        }
    }

    @SuppressLint("CheckResult")
    fun currentMatchesRX() {

        val matchesSingle = repo.currentMatchesRX()

        matchesSingle.observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribeWith(object : SingleObserver<CurrentMatches> {
                override fun onSubscribe(d: Disposable) {
                }

                override fun onSuccess(t: CurrentMatches) {
                }

                override fun onError(e: Throwable) {
                }
            })

        val publishSubject = PublishSubject.create<String>()
        publishSubject.
        observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe {
              Log.d(TAG,it)
            }

        publishSubject.onNext("hello")


    }
}


//fun currentMatches() {
//        viewModelScope.launch {
//            try {
//                val currentMatches = repo.currentMatches()
//                if (currentMatches.isSuccessful)
//                    _currentMatches.value = currentMatches.body()
//                else
//                    _currentMatchesFailed.value = "CurrentMatches API failed"
//            } catch (e: Exception) {
//                _currentMatchesFailed.value = e.message
//                e.printStackTrace()
//            }
//        }
//}
//fun ViewModel.concate(a: Int, b: Int, result: (String)-> Unit) {
//    val s = (a+b).toString()
//
//    return result(s)
//}
//
//
//concate(1, 2) { result ->
//
//}
//concate(1, 2, { result ->
//
//})