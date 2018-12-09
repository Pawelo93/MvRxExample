package com.mvrxopenfm.base

import android.app.Application
import com.mvrxopenfm.data.network.ApiService
import com.mvrxopenfm.domain.useCase.LoadChannelGroupsUseCase
import com.mvrxopenfm.domain.useCase.LoadPlaylistForChannelUseCase
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import org.koin.android.ext.android.startKoin
import org.koin.dsl.module.Module
import org.koin.dsl.module.applicationContext
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory

class MvRxApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin(this, listOf(apiServiceModule, loadChannelsModule))
    }
}