package com.dmitri.projectapifootballv2.model.repo

import com.dmitri.projectapifootballv2.api.IDataSource
import com.dmitri.projectapifootballv2.model.cache.TeamsCache
import com.dmitri.projectapifootballv2.model.entity.Teams
import com.dmitri.projectapifootballv2.network.NetworkStatus
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class RetrofitTeamsRepo @Inject constructor(
    private val api: IDataSource,
    private val networkStatus: NetworkStatus,
    private val cache: TeamsCache
) : TeamsRepo {
    override fun getTeams(leagueName: Int, teams: List<Teams>): Single<List<Teams>> =
        networkStatus.isOnlineSingle().flatMap { isOnLine ->
            if (isOnLine) {
                api.getTeams(teams.joinToString(",")).flatMap {
                    cache.putTeams(leagueName, it).andThen(Single.just(it))
                }
            } else {
                cache.getTeams(leagueName)
            }
        }
}