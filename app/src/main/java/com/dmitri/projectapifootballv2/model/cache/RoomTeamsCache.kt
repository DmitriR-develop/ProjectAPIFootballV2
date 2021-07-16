package com.dmitri.projectapifootballv2.model.cache

import com.dmitri.projectapifootballv2.model.entity.Teams
import com.dmitri.projectapifootballv2.model.entity.room.RoomTeams
import com.dmitri.projectapifootballv2.model.entity.room.database.Database
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class RoomTeamsCache @Inject constructor(private val db: Database) : TeamsCache {
    override fun putTeams(league: Int, teams: List<Teams>): Completable {
        val roomLeagues = league.let {
            db.leaguesDao.findByName(it)
        } ?: throw java.lang.RuntimeException("No such league in DB")
        val roomTeams = teams.map {
            RoomTeams(
                it.id,
                roomLeagues.name,
                it.country,
                it.code,
                it.season,
                it.team,
                it.type,
                it.current,
                it.search,
                it.last
            )
        }
        return db.teamsDao.insert(roomTeams)
    }

    override fun getTeams(league: Int): Single<List<Teams>> = Single.fromCallable {
        val roomLeagues = league.let {
            db.leaguesDao.findByName(it)
        } ?: throw java.lang.RuntimeException("No such league in DB")
        db.teamsDao.findForLeagues(roomLeagues.id).map {
            Teams(
                it.id,
                it.name,
                it.country,
                it.code,
                it.season,
                it.team,
                it.type,
                it.current,
                it.search,
                it.last
            )
        }
    }
}