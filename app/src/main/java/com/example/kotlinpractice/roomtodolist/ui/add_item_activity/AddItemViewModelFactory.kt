package com.example.kotlinpractice.roomtodolist.ui.add_item_activity

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.example.kotlinpractice.roomtodolist.persistance.Repository
import com.example.kotlinpractice.roomtodolist.utils.SchedulersFacade

class AddItemViewModelFactory(private val repository: Repository, private val schedulersFacade: SchedulersFacade) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AddItemViewModel::class.java)) {
            return AddItemViewModel(repository, schedulersFacade) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}