package com.example.kotlinpractice.roomtodolist.sync

import android.arch.lifecycle.LiveData
import com.example.kotlinpractice.roomtodolist.api.ApiResponse
import com.example.kotlinpractice.roomtodolist.model.Response
import retrofit2.http.GET

interface AppApi {

    @GET("/ToDoList.json")
    fun getAllBooks(): LiveData<ApiResponse<Response>>
}