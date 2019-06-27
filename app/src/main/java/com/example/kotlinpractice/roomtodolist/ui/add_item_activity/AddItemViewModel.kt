package com.example.kotlinpractice.roomtodolist.ui.add_item_activity

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.text.Editable
import android.text.TextWatcher
import com.example.kotlinpractice.roomtodolist.model.Task
import com.example.kotlinpractice.roomtodolist.persistance.Repository


class AddItemViewModel(val repository: Repository) : ViewModel() {

    private var priority = "Normal"
    private lateinit var toDoText: String
    val itemAdded = MutableLiveData<Boolean>()

    var textWatcher = object : TextWatcher {
        override fun afterTextChanged(s: Editable?) {
            // Do nothing.
        }

        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            // Do nothing.
        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            toDoText = s.toString()
        }

    }

    fun addItem() {
        val itemToDo = Task(toDoText, priority)
        repository.addToDoItem(itemToDo)
        itemAdded.value = true
    }

    fun onCheckedChanged(priority: String) {
        this.priority = priority
    }
}