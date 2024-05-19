package com.example.komalkotlin2024.roomdb

import android.annotation.SuppressLint
import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.room.Room
import com.example.komalkotlin2024.KomalApplication
import io.reactivex.Completable
import io.reactivex.CompletableObserver
import io.reactivex.Scheduler
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.observers.DisposableMaybeObserver
import io.reactivex.rxjava3.core.MaybeObserver
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch

class LoginViewModel( application: Application): AndroidViewModel(application) {

    val database = Room.databaseBuilder(application.applicationContext, EmployeeDatabase::class.java,"employee_db").build()

    val employeeDAO = database.employeeDao()
    val employees = MutableLiveData<List<Employee>>()
    val employee = MutableLiveData<Employee>()

    val status = MutableLiveData<String>()


    @SuppressLint("CheckResult")
    fun insertEmployee(employee: Employee){
            employeeDAO.insertEmployee(employee)
                .observeOn(AndroidSchedulers.mainThread())
            .observeOn(Schedulers.io())
            .subscribeWith(object: CompletableObserver{
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
        employeeDAO.getEmployee(name, pwd).observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe {
                    employee.postValue(it)
                }

            }



    fun getAllEmployees(): LiveData<List<Employee>> {
            return employeeDAO.getAllEmployees()
        }

    @SuppressLint("CheckResult")
    fun deleteEmployee(employee: Employee){
        Single.just ( employeeDAO.deleteEmployee(employee) )
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe({
            v -> status.postValue("deleted row id $v for ${employee.name} ${employee.password}") },{
            e -> status.postValue("deleted error  for ${employee.name} ${employee.password}")
        })
    }

    @SuppressLint("CheckResult")
    fun getAllEmployeesFlowable(){
        employeeDAO.getAllEmployeesFlowable()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                employees.postValue(it)
            }
    }

}