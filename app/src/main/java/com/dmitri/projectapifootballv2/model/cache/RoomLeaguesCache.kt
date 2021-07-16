package com.dmitri.projectapifootballv2.model.cache

import com.dmitri.projectapifootballv2.model.entity.Leagues
import com.dmitri.projectapifootballv2.model.entity.room.RoomLeagues
import com.dmitri.projectapifootballv2.model.entity.room.database.Database
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class RoomLeaguesCache @Inject constructor(private val db: Database) : LeaguesCache {
    override fun putLeagues(leagues: List<Leagues>): Completable {
        val roomLeagues = leagues.map { leaguesItem ->
            RoomLeagues(
                leaguesItem.id,
                leaguesItem.name,
                leaguesItem.country,
                leaguesItem.code,
                leaguesItem.season,
                leaguesItem.team,
                leaguesItem.type,
                leaguesItem.current,
                leaguesItem.search,
                leaguesItem.last
            )
        }
        return db.leaguesDao.insert(roomLeagues)
    }

    override fun getLeagues() = Single.fromCallable {
        db.leaguesDao.getAll().map { leaguesItem ->
            Leagues(
                leaguesItem.id,
                leaguesItem.name,
                leaguesItem.country,
                leaguesItem.code,
                leaguesItem.season,
                leaguesItem.team,
                leaguesItem.type,
                leaguesItem.current,
                leaguesItem.search,
                leaguesItem.last
            )
        }
    }

    override fun getLeague(id: Int): Single<Leagues> = Single.fromCallable {
        val roomLeagues = db.leaguesDao.findByName(id)
        roomLeagues?.let { leaguesItem ->
            Leagues(
                leaguesItem.id,
                leaguesItem.name,
                leaguesItem.country,
                leaguesItem.code,
                leaguesItem.season,
                leaguesItem.team,
                leaguesItem.type,
                leaguesItem.current,
                leaguesItem.search,
                leaguesItem.last
            )
        }
    }

}