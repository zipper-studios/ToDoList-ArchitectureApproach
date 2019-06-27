package com.example.kotlinpractice.roomtodolist.dagger.injection.component

import com.example.kotlinpractice.roomtodolist.ui.add_item_activity.AddItemActivity
import com.example.kotlinpractice.roomtodolist.ui.add_item_activity.AddItemActivityModule
import com.example.kotlinpractice.roomtodolist.ui.main_activity.MainActivity
import com.example.kotlinpractice.roomtodolist.ui.main_activity.MainActivityModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilder {

    @ContributesAndroidInjector(modules = [MainActivityModule::class])
    internal abstract fun bindMainActivity(): MainActivity

    @ContributesAndroidInjector(modules = [AddItemActivityModule::class])
    internal abstract fun bindItemActivity(): AddItemActivity
}