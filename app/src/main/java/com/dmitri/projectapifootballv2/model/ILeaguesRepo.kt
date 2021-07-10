package com.dmitri.projectapifootballv2.model

import io.reactivex.rxjava3.core.Single

interface ILeaguesRepo {
    fun getLeagues(): Single<List<Leagues>>
    fun getLeaguesById(id: Int): Single<List<Leagues>>
    fun getTeams(team: Int): Single<List<Teams>>
}