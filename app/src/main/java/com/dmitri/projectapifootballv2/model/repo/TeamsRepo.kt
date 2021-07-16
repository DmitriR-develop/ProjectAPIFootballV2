package com.dmitri.projectapifootballv2.model.repo

import com.dmitri.projectapifootballv2.model.entity.Teams
import io.reactivex.rxjava3.core.Single

interface TeamsRepo {
    fun getTeams(leagueName: Int, teams: List<Teams>): Single<List<Teams>>
}