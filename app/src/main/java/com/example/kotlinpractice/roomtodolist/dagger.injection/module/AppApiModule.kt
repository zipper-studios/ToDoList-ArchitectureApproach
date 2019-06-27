package com.example.kotlinpractice.roomtodolist.dagger.injection.module

import com.example.kotlinpractice.roomtodolist.sync.AppApi
import com.example.kotlinpractice.roomtodolist.utils.Constants
import com.example.kotlinpractice.roomtodolist.utils.LiveDataCallAdapterFactory
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Named
import javax.inject.Singleton

@Module
internal class AppApiModule {

    @Provides
    @Singleton
    @Named("app_url")
    fun provideAppBaseUrl(): String = Constants.BASE_URL

    @Provides
    @Singleton
    @Named("app_okhttp")
    fun provideOkHttpClient(): OkHttpClient {
        return with(OkHttpClient.Builder()) {
            connectTimeout(Constants.TIMEOUT_INMILIS, TimeUnit.MILLISECONDS)
            build()
        }
    }

    @Provides
    @Singleton
    @Named("app_retrofit")
    fun provideRetrofit(@Named("app_url") baseUrl: String, @Named("app_okhttp") client: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(LiveDataCallAdapterFactory())
            .build()
    }

    @Provides
    @Singleton
    fun provideAppApiServices(@Named("app_retrofit") retrofit: Retrofit): AppApi = retrofit.create(AppApi::class.java)
}