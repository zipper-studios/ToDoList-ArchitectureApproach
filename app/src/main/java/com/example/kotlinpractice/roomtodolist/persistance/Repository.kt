package com.example.kotlinpractice.roomtodolist.persistance

import android.arch.lifecycle.LiveData
import com.example.kotlinpractice.roomtodolist.api.ApiResponse
import com.example.kotlinpractice.roomtodolist.model.Task
import com.example.kotlinpractice.roomtodolist.sync.AppApi
import com.example.kotlinpractice.roomtodolist.utils.AppExecutors
import com.example.kotlinpractice.roomtodolist.utils.Resource
import io.reactivex.Completable


class Repository(private val appExecutors: AppExecutors, private val db: ToDoDatabase, private val appApi: AppApi) {

    fun addTask(task: Task): Completable {
        return appApi.postTask(task)
    }

    private fun getBooks(): LiveData<ApiResponse<List<Task>>> {
        return appApi.getAllTasks()
    }

    fun loadRepos(): LiveData<Resource<List<Task>>> {
        return object : NetworkBoundResource<List<Task>, List<Task>>(appExecutors) {

            override fun saveCallResult(item: List<Task>) {
                db.toDoDao().insertTasks(item)
            }

            override fun shouldFetch(data: List<Task>?): Boolean {
                return true
            }

            override fun loadFromDb(): LiveData<List<Task>> {
                return db.toDoDao().getAllTasks()
            }

            override fun createCall(): LiveData<ApiResponse<List<Task>>> {
                return getBooks()
            }

        }.asLiveData()
    }
}