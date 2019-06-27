package com.example.kotlinpractice.roomtodolist.ui.main_activity

import com.example.kotlinpractice.roomtodolist.persistance.Repository
import dagger.Module
import dagger.Provides

@Module
class MainActivityModule {

    @Provides
    internal fun provideChatViewModelFactory(repository: Repository): MainViewModelFactory {
        return MainViewModelFactory(repository)
    }
}