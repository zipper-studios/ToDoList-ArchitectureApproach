package com.example.kotlinpractice.roomtodolist.dagger.injection.module

import com.example.kotlinpractice.roomtodolist.persistance.Repository
import com.example.kotlinpractice.roomtodolist.persistance.ToDoDatabase
import com.example.kotlinpractice.roomtodolist.sync.AppApi
import com.example.kotlinpractice.roomtodolist.utils.AppExecutors
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepositoryModule {

    @Provides
    @Singleton
    internal fun provideRepository(appExecutors: AppExecutors, db: ToDoDatabase, appApi: AppApi): Repository {
        return Repository(appExecutors, db, appApi)
    }
}
