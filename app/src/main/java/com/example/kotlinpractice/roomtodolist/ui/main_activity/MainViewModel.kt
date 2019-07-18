package com.example.kotlinpractice.roomtodolist.ui.main_activity

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Transformations
import android.arch.lifecycle.ViewModel
import com.example.kotlinpractice.roomtodolist.model.Task
import com.example.kotlinpractice.roomtodolist.persistance.Repository
import com.example.kotlinpractice.roomtodolist.utils.Resource

class MainViewModel(val repository: Repository) : ViewModel() {

    private val _refreshItems = MutableLiveData<Unit>()

    val mainAdapter = MainAdapter()
    var itemsList: LiveData<Resource<List<Task>>> = Transformations.switchMap(_refreshItems) { repository.loadRepos() }

    fun updateList(it: List<Task>) {
        mainAdapter.updateList(it)
    }

    fun onResume() {
        _refreshItems.value = Unit
    }
}