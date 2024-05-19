package com.example.komalkotlin2024.roomdb

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Maybe
import io.reactivex.Single

@Dao
interface EmployeeDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertEmployee(employee: Employee): Completable

    @Delete
    fun deleteEmployee(employee: Employee): Int

    @Query("SELECT * FROM employee")
    fun getAllEmployees(): LiveData<List<Employee>>

    @Query("SELECT * FROM employee")
    fun getAllEmployeesFlowable(): Flowable<List<Employee>>

    @Query("SELECT * FROM employee WHERE name =:name AND password=:pwd")
    fun getEmployee(name: String, pwd:String): Maybe<Employee>

    @Update(onConflict = OnConflictStrategy.IGNORE)
    fun updateEmployee(employee: Employee)

}