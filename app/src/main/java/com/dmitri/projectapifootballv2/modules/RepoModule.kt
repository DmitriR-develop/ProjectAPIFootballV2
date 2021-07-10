package com.dmitri.projectapifootballv2.modules

import com.dmitri.projectapifootballv2.api.IDataSource
import com.dmitri.projectapifootballv2.model.ILeaguesRepo
import com.dmitri.projectapifootballv2.model.RetrofitLeaguesRepo
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepoModule {
    @Singleton
    @Provides
    fun leaguesRepo(api: IDataSource): ILeaguesRepo = RetrofitLeaguesRepo(api)
}