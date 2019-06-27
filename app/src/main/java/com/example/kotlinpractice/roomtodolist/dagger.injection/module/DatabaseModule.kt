package com.example.kotlinpractice.roomtodolist.dagger.injection.module

import android.arch.persistence.room.Room
import android.content.Context
import com.example.kotlinpractice.roomtodolist.dagger.injection.DbName
import com.example.kotlinpractice.roomtodolist.persistance.ToDoDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
internal class DatabaseModule {
    @Provides
    @DbName
    fun provideDbName() = "ToDoList.db"

    @Provides
    @Singleton
    fun provideToDoDatabase(appContext: Context, @DbName dbName: String): ToDoDatabase {
        return Room.databaseBuilder(
            appContext,
            ToDoDatabase::class.java, dbName
        ).build()
    }
}