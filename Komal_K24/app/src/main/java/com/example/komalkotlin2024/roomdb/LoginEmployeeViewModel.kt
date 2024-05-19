package com.example.komalkotlin2024.roomdb

import android.annotation.SuppressLint
import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import io.reactivex.CompletableObserver
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class LoginEmployeeViewModel(val application: Application, private val repository: EmployeeDAO):ViewModel() {
    class LoginEmployeeViewModelFactory constructor(val application: Application, private val repository: EmployeeDAO): ViewModelProvider.Factory {

        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return if (modelClass.isAssignableFrom(LoginEmployeeViewModel::class.java!!)) {
                LoginEmployeeViewModel(application, this.repository) as T
            } else {
                throw IllegalArgumentException("ViewModel Not Found")
            }
        }
    }

    val employees = MutableLiveData<List<Employee>>()
    val employee = MutableLiveData<Employee>()

    val status = MutableLiveData<String>()


    @SuppressLint("CheckResult")
    fun insertEmployee(employee: Employee){
        repository.insertEmployee(employee)
            .observeOn(AndroidSchedulers.mainThread())
            .observeOn(Schedulers.io())
            .subscribeWith(object: CompletableObserver {
                override fun onSubscribe(d: Disposable) {
                }

                override fun onComplete() {
                    status.postValue("Insertion successfull")
                }

                override fun onError(e: Throwable) {
                }

            })


    }

    @SuppressLint("CheckResult")
    fun getEmployee(name:String, pwd:String){
        repository.getEmployee(name, pwd).observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe {
                employee.postValue(it)
            }

    }



    fun getAllEmployees(): LiveData<List<Employee>> {
        return repository.getAllEmployees()
    }

    @SuppressLint("CheckResult")
    fun deleteEmployee(employee: Employee){
        Single.just ( repository.deleteEmployee(employee) )
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe({
                    v -> status.postValue("deleted row id $v for ${employee.name} ${employee.password}") },{
                    e -> status.postValue("deleted error  for ${employee.name} ${employee.password}")
            })
    }

    @SuppressLint("CheckResult")
    fun getAllEmployeesFlowable(){
        repository.getAllEmployeesFlowable()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                employees.postValue(it)
            }
    }
}