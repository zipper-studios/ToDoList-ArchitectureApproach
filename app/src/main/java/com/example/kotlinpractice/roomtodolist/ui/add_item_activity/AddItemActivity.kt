package com.example.kotlinpractice.roomtodolist.ui.add_item_activity

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.view.View
import com.example.kotlinpractice.roomtodolist.R
import com.example.kotlinpractice.roomtodolist.databinding.ActivityAddItemBindingImpl
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_add_item.*
import javax.inject.Inject


class AddItemActivity : DaggerAppCompatActivity() {

    private lateinit var viewModel: AddItemViewModel

    @Inject
    lateinit var viewModelFactory: AddItemViewModelFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityAddItemBindingImpl = DataBindingUtil.setContentView(this, R.layout.activity_add_item)
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(AddItemViewModel::class.java)
        binding.viewModel = viewModel

        viewModel.taskAdded.observe(this, Observer { itemAdded ->
            itemAdded?.let {
                if (itemAdded) {
                    finish()
                } else {
                    aia_progress_bar.visibility = View.VISIBLE
                }
            }
        })
    }
}
