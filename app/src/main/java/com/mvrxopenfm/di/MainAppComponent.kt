package com.mvrxopenfm.di

import com.mvrxopenfm.base.MvRxApplication
import dagger.BindsInstance
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        AppAssistedModule::class,
        BuildersModule::class,
        AppModule::class,
        NetworkModule::class
    ]
)

interface MainAppComponent {
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(applicatiion: MvRxApplication): Builder

        fun build(): MainAppComponent
    }

    fun inject(app: MvRxApplication)
}