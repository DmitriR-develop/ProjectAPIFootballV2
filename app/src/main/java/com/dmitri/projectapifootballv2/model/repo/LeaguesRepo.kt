package com.dmitri.projectapifootballv2.model.repo

import com.dmitri.projectapifootballv2.model.entity.Leagues
import com.dmitri.projectapifootballv2.model.entity.Teams
import io.reactivex.rxjava3.core.Single

interface LeaguesRepo {
    fun getLeagues(): Single<List<Leagues>>
    fun getLeaguesById(id: Int): Single<Leagues>
}