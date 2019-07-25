package com.example.kotlinpractice.roomtodolist.ui.add_item_activity

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.text.Editable
import android.text.TextWatcher
import com.example.kotlinpractice.roomtodolist.model.Task
import com.example.kotlinpractice.roomtodolist.persistance.Repository
import com.example.kotlinpractice.roomtodolist.utils.SchedulersFacade
import io.reactivex.disposables.Disposable
import timber.log.Timber


class AddItemViewModel(val repository: Repository, val schedulersFacade: SchedulersFacade) : ViewModel() {

    private var priority = "Normal"
    private lateinit var toDoText: String
    private lateinit var disposable: Disposable

    val taskAdded = MutableLiveData<Boolean>()

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
        val task = Task(toDoText, priority)

        disposable = repository.addTask(task)
            .subscribeOn(schedulersFacade.io())
            .observeOn(schedulersFacade.ui())
            .subscribe(
                { taskAdded.value = true },
                { error ->
                    run {
                        Timber.d(error.localizedMessage)
                    }
                }
            )
    }

    fun onCheckedChanged(priority: String) {
        this.priority = priority
    }

    override fun onCleared() {
        super.onCleared()
        disposable.dispose()
    }
}