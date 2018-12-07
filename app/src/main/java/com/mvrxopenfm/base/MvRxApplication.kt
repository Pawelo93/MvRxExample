package com.mvrxopenfm.base

import android.app.Application
import com.mvrxopenfm.data.network.ApiService
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import org.koin.android.ext.android.startKoin
import org.koin.dsl.module.Module
import org.koin.dsl.module.applicationContext
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory

class MvRxApplication : Application() {

    private val apiServiceModule : Module = applicationContext {
        bean {
            val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
            val retrofit = Retrofit.Builder()
                .baseUrl("https://open.fm/radio/api/v3/")
                .addConverterFactory(MoshiConverterFactory.create(moshi))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
            retrofit.create(ApiService::class.java)
        }
    }

    override fun onCreate() {
        super.onCreate()
        startKoin(this, listOf(apiServiceModule))
    }
}