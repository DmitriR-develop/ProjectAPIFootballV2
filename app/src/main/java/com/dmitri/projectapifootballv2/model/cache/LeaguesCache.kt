package com.dmitri.projectapifootballv2.model.cache

import com.dmitri.projectapifootballv2.model.entity.Leagues
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

interface LeaguesCache {
    fun putLeagues(leagues: List<Leagues>): Completable
    fun getLeagues(): Single<List<Leagues>>
    fun getLeague(id: Int): Single<Leagues>
}