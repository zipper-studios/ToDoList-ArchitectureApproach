package com.example.kotlinpractice.roomtodolist.utils

import java.util.concurrent.TimeUnit

class Constants {

    companion object {
        val TIMEOUT_INMILIS = TimeUnit.SECONDS.toMillis(30)
        val BASE_URL = "https://roompractice-9f7e1.firebaseio.com/"
    }

}