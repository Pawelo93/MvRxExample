package com.mvrxopenfm.di

import android.content.Context
import com.mvrxopenfm.base.MvRxApplication
import dagger.Module
import dagger.Provides

@Module
class AppModule {

    @Provides
    fun applicationContext(app: MvRxApplication): Context {
        return app
    }
}