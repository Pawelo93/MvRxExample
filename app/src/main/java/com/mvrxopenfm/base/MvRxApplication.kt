package com.mvrxopenfm.base

import android.app.Application
import android.support.v4.app.Fragment
import com.mvrxopenfm.di.DaggerMainAppComponent
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import javax.inject.Inject

class MvRxApplication : Application(), HasSupportFragmentInjector {

    @Inject
    lateinit var fragmentInjector: DispatchingAndroidInjector<Fragment>

    override fun onCreate() {
        super.onCreate()
        DaggerMainAppComponent.builder()
            .application(this)
            .build()
            .inject(this)
    }

    override fun supportFragmentInjector() = fragmentInjector
}