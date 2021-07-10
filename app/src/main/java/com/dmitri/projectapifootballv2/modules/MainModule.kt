package com.dmitri.projectapifootballv2.modules

import com.dmitri.projectapifootballv2.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class MainModule {
    @ContributesAndroidInjector
    abstract fun bindMainActivity(): MainActivity
}