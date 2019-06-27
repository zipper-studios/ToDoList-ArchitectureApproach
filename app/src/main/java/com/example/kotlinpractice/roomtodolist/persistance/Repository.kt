package com.example.kotlinpractice.roomtodolist.persistance

import android.arch.lifecycle.LiveData
import com.example.kotlinpractice.roomtodolist.api.ApiResponse
import com.example.kotlinpractice.roomtodolist.model.Response
import com.example.kotlinpractice.roomtodolist.model.Task
import com.example.kotlinpractice.roomtodolist.sync.AppApi
import com.example.kotlinpractice.roomtodolist.utils.AppExecutors
import com.example.kotlinpractice.roomtodolist.utils.IndexUtil.Companion.getIndexForCurrentTask
import com.example.kotlinpractice.roomtodolist.utils.Resource
import com.google.firebase.database.*


class Repository(private val appExecutors: AppExecutors, private val db: ToDoDatabase, private val appApi: AppApi) {

    fun addToDoItem(itemToDo: Task) {
        val firebaseReference = FirebaseDatabase.getInstance().reference.child("ToDoList").child("taskList")

        firebaseReference.addListenerForSingleValueEvent(object : ValueEventListener {

            override fun onDataChange(dataSnapshot: DataSnapshot) {
                insertNewTask(dataSnapshot, firebaseReference, itemToDo)
            }

            override fun onCancelled(databaseError: DatabaseError) {
                //Error
            }
        })
    }

    fun insertNewTask(dataSnapshot: DataSnapshot, firebaseReference: DatabaseReference, itemToDo: Task) {
        val lastItemIndex = dataSnapshot.childrenCount - 1
        val taskToBeInsertedReference: DatabaseReference

        if (lastItemIndex >= 0) {
            val currentTaskFirebaseIndex = getIndexForCurrentTask(dataSnapshot, lastItemIndex)
            taskToBeInsertedReference = firebaseReference.child(currentTaskFirebaseIndex)
        } else {
            taskToBeInsertedReference = firebaseReference.child("0")
        }
        taskToBeInsertedReference.setValue(itemToDo)
    }

    private fun getBooks(): LiveData<ApiResponse<Response>> {
        return appApi.getAllBooks()
    }

    fun loadRepos(): LiveData<Resource<List<Task>>> {
        return object : NetworkBoundResource<List<Task>, Response>(appExecutors) {

            override fun saveCallResult(item: Response) {
                item.taskList?.let {
                    db.toDoDao().insertTasks(it)
                }
            }

            override fun shouldFetch(data: List<Task>?): Boolean {
                return true
            }

            override fun loadFromDb(): LiveData<List<Task>> {
                return db.toDoDao().getAllTasks()
            }

            override fun createCall(): LiveData<ApiResponse<Response>> {
                return getBooks()
            }

        }.asLiveData()
    }
}