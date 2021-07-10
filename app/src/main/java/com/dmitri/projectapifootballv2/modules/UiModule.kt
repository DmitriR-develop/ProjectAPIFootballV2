package com.dmitri.projectapifootballv2.modules

import com.dmitri.projectapifootballv2.fragments.LeaguesFragment
import com.dmitri.projectapifootballv2.fragments.TeamsFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class UiModule {
    @ContributesAndroidInjector
    abstract fun bindLeaguesFragment(): LeaguesFragment

    @ContributesAndroidInjector
    abstract fun bindTeamsFragment(): TeamsFragment
}