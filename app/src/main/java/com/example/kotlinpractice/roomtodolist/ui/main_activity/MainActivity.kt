package com.example.kotlinpractice.roomtodolist.ui.main_activity

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.os.PersistableBundle
import android.view.View
import com.example.kotlinpractice.roomtodolist.R
import com.example.kotlinpractice.roomtodolist.databinding.ActivityMainBindingImpl
import com.example.kotlinpractice.roomtodolist.ui.add_item_activity.AddItemActivity
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

class MainActivity : DaggerAppCompatActivity() {

    private lateinit var viewModel: MainViewModel

    @Inject
    lateinit var viewModelFactory: MainViewModelFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityMainBindingImpl = DataBindingUtil.setContentView(this, R.layout.activity_main)
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(MainViewModel::class.java)
        binding.viewModel = viewModel

        viewModel.itemsList.observe(this, Observer { list ->
            list?.data?.let {
                viewModel.updateList(it)
            }
        })
    }

    fun startAddItemActivity(v: View) {
        val intent = Intent(this, AddItemActivity::class.java)
        startActivity(intent)
    }

    override fun onResume() {
        super.onResume()
        viewModel.onResume()
    }
}
