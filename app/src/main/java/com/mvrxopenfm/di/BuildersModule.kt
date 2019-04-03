package com.mvrxopenfm.di

import com.mvrxopenfm.MainActivity
import com.mvrxopenfm.ui.channels.ChannelFragment
import com.mvrxopenfm.ui.details.DetailsFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class BuildersModule {

    @ContributesAndroidInjector
    abstract fun bindMainActivity(): MainActivity

    @ContributesAndroidInjector
    abstract fun binChannelFragment(): ChannelFragment

    @ContributesAndroidInjector
    abstract fun bindDetailsFragment(): DetailsFragment
}