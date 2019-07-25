package com.example.kotlinpractice.roomtodolist.ui.add_item_activity

import com.example.kotlinpractice.roomtodolist.persistance.Repository
import com.example.kotlinpractice.roomtodolist.utils.SchedulersFacade
import dagger.Module
import dagger.Provides

@Module
class AddItemActivityModule {

    @Provides
    internal fun provideChatViewModelFactory(repository: Repository, schedulersFacade: SchedulersFacade): AddItemViewModelFactory {
        return AddItemViewModelFactory(repository, schedulersFacade)
    }
}