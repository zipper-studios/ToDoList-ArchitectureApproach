package com.example.kotlinpractice.roomtodolist.ui.add_item_activity

import com.example.kotlinpractice.roomtodolist.persistance.Repository
import dagger.Module
import dagger.Provides

@Module
class AddItemActivityModule {

    @Provides
    internal fun provideChatViewModelFactory(repository: Repository): AddItemViewModelFactory {
        return AddItemViewModelFactory(repository)
    }
}