package com.dmitri.projectapifootballv2.model

import com.dmitri.projectapifootballv2.api.IDataSource
import io.reactivex.rxjava3.core.Single

class RetrofitLeaguesRepo(private val api: IDataSource) : ILeaguesRepo {

    override fun getLeagues() = api.getLeagues()

    override fun getLeaguesById(id: Int): Single<List<Leagues>> = api.getLeaguesById(id)

    override fun getTeams(team: Int): Single<List<Teams>> = api.getTeams(team)

}