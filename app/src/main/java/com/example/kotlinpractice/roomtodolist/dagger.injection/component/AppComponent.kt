package com.example.kotlinpractice.roomtodolist.dagger.injection.component

import android.app.Application
import com.example.kotlinpractice.roomtodolist.RoomToDoListApp
import com.example.kotlinpractice.roomtodolist.dagger.injection.module.AppApiModule
import com.example.kotlinpractice.roomtodolist.dagger.injection.module.AppModule
import com.example.kotlinpractice.roomtodolist.dagger.injection.module.DatabaseModule
import com.example.kotlinpractice.roomtodolist.dagger.injection.module.RepositoryModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [AndroidSupportInjectionModule::class, AppModule::class, RepositoryModule::class, ActivityBuilder::class, DatabaseModule::class, AppApiModule::class]
)
interface AppComponent : AndroidInjector<DaggerApplication> {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }

    fun inject(app: RoomToDoListApp)
}