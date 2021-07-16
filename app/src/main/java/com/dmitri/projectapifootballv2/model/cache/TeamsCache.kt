package com.dmitri.projectapifootballv2.model.cache

import com.dmitri.projectapifootballv2.model.entity.Teams
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

interface TeamsCache {
    fun putTeams(league: Int, teams: List<Teams>): Completable
    fun getTeams(league: Int): Single<List<Teams>>
}