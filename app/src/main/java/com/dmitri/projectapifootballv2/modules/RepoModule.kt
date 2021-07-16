package com.dmitri.projectapifootballv2.modules

import com.dmitri.projectapifootballv2.api.IDataSource
import com.dmitri.projectapifootballv2.model.cache.LeaguesCache
import com.dmitri.projectapifootballv2.model.cache.TeamsCache
import com.dmitri.projectapifootballv2.model.repo.LeaguesRepo
import com.dmitri.projectapifootballv2.model.repo.RetrofitLeaguesRepo
import com.dmitri.projectapifootballv2.model.repo.RetrofitTeamsRepo
import com.dmitri.projectapifootballv2.model.repo.TeamsRepo
import com.dmitri.projectapifootballv2.network.NetworkStatus
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepoModule {
    @Singleton
    @Provides
    fun leaguesRepo(
        api: IDataSource,
        networkStatus: NetworkStatus,
        cache: LeaguesCache
    ): LeaguesRepo = RetrofitLeaguesRepo(api, networkStatus, cache)

    @Singleton
    @Provides
    fun teamsRepo(
        api: IDataSource,
        networkStatus: NetworkStatus,
        cache: TeamsCache
    ): TeamsRepo = RetrofitTeamsRepo(api, networkStatus, cache)
}