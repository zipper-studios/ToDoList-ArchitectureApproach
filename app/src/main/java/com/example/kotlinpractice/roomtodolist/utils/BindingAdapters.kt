package com.example.kotlinpractice.roomtodolist.utils

import android.databinding.BindingAdapter
import android.support.v7.widget.RecyclerView
import android.text.TextWatcher
import android.widget.EditText
import com.example.kotlinpractice.roomtodolist.ui.main_activity.MainAdapter


object BindingAdapters {

    @JvmStatic
    @BindingAdapter("textChangedListener")
    fun addTextChangedListener(editText: EditText, textWatcher: TextWatcher) {
        editText.addTextChangedListener(textWatcher)
    }

    @JvmStatic
    @BindingAdapter("setAdapter")
    fun setAdapter(recycler: RecyclerView, mainAdapter: MainAdapter) {
        recycler.adapter = mainAdapter
    }

}