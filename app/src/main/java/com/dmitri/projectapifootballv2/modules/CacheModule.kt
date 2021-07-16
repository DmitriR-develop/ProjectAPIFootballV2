package com.dmitri.projectapifootballv2.modules

import com.dmitri.projectapifootballv2.model.cache.LeaguesCache
import com.dmitri.projectapifootballv2.model.cache.RoomLeaguesCache
import com.dmitri.projectapifootballv2.model.cache.RoomTeamsCache
import com.dmitri.projectapifootballv2.model.cache.TeamsCache
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class CacheModule {
    @Singleton
    @Provides
    fun leaguesCache(
        db: com.dmitri.projectapifootballv2.model.entity.room.database.Database
    ): LeaguesCache = RoomLeaguesCache(db)

    @Singleton
    @Provides
    fun teamsCache(
        db: com.dmitri.projectapifootballv2.model.entity.room.database.Database
    ): TeamsCache = RoomTeamsCache(db)
}