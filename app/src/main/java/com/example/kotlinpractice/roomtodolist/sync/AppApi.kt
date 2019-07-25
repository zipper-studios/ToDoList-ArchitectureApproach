package com.example.kotlinpractice.roomtodolist.sync

import android.arch.lifecycle.LiveData
import com.example.kotlinpractice.roomtodolist.api.ApiResponse
import com.example.kotlinpractice.roomtodolist.model.Response
import com.example.kotlinpractice.roomtodolist.model.Task
import io.reactivex.Completable
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface AppApi {

    @GET("/ToDoList.json")
    fun getAllTasks(): LiveData<ApiResponse<List<Task>>>

    @POST("/ToDoList/taskList.json")
    fun postTask(@Body task: Task): Completable
}