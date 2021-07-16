package com.dmitri.projectapifootballv2.model.repo

import com.dmitri.projectapifootballv2.api.IDataSource
import com.dmitri.projectapifootballv2.model.cache.LeaguesCache
import com.dmitri.projectapifootballv2.model.entity.Leagues
import com.dmitri.projectapifootballv2.model.entity.Teams
import com.dmitri.projectapifootballv2.network.NetworkStatus
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

class RetrofitLeaguesRepo @Inject constructor(
    private val api: IDataSource,
    private val networkStatus: NetworkStatus,
    private val cache: LeaguesCache
) : LeaguesRepo {
    override fun getLeagues(): Single<List<Leagues>> =
        networkStatus.isOnlineSingle().flatMap { isOnLine ->
            if (isOnLine) {
                api.getLeagues().flatMap { leagues ->
                    cache.putLeagues(leagues)
                        .andThen(Single.just(leagues))
                }
            } else {
                cache.getLeagues()
            }
        }.subscribeOn(Schedulers.io())

    override fun getLeaguesById(id: Int): Single<Leagues> =
        networkStatus.isOnlineSingle().flatMap { isOnLine ->
            if (isOnLine) {
                api.getLeaguesById(id)
            } else {
                cache.getLeague(id)
            }
        }.subscribeOn(Schedulers.io())
}