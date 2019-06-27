package com.example.kotlinpractice.roomtodolist.persistance

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import com.example.kotlinpractice.roomtodolist.dao.ToDoDao
import com.example.kotlinpractice.roomtodolist.model.Task

@Database(entities = [Task::class], version = 1)
abstract class ToDoDatabase : RoomDatabase() {

    abstract fun toDoDao(): ToDoDao
}