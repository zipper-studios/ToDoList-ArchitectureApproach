package com.example.kotlinpractice.roomtodolist.utils

import com.google.firebase.database.DataSnapshot

class IndexUtil {

    companion object {

        fun getIndexForCurrentTask(dataSnapshot: DataSnapshot, lastItemIndex: Long): String {
            val taskList = dataSnapshot.children
            val lastTask = taskList.elementAt(lastItemIndex.toInt())
            return (lastTask.key!!.toInt() +1).toString()
        }
    }
}