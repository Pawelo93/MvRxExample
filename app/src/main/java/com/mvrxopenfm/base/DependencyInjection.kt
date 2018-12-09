package com.mvrxopenfm.base

import com.mvrxopenfm.data.network.ApiService
import com.mvrxopenfm.domain.useCase.LoadChannelGroupsUseCase
import com.mvrxopenfm.domain.useCase.LoadPlaylistForChannelUseCase
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module.Module
import org.koin.dsl.module.applicationContext
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory

val apiServiceModule : Module = applicationContext {
    bean {

        val httpLoggingInterceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BASIC
        }

        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(httpLoggingInterceptor)
            .build()

        val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()

        val retrofit = Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl("https://open.fm/radio/api/v3/")
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
        retrofit.create(ApiService::class.java)
    }
}

val loadChannelsModule: Module = applicationContext {
    factory {
        LoadChannelGroupsUseCase(get())
    }

    factory {
        LoadPlaylistForChannelUseCase(get())
    }
}