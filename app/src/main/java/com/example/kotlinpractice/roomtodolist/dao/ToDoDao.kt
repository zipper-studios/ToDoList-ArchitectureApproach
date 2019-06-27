package com.example.kotlinpractice.roomtodolist.dao

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import com.example.kotlinpractice.roomtodolist.model.Task

@Dao
interface ToDoDao {

    @Query("SELECT * from tasks")
    fun getAllTasks(): LiveData<List<Task>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTasks(list: List<Task>)
}