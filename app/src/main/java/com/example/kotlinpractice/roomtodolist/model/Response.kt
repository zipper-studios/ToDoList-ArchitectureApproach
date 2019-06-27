package com.example.kotlinpractice.roomtodolist.model

import com.google.gson.annotations.SerializedName

data class Response(@SerializedName("taskList")
                    val taskList: List<Task>?)