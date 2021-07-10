package com.dmitri.projectapifootballv2.navigation

import com.dmitri.projectapifootballv2.fragments.LeaguesFragment
import com.dmitri.projectapifootballv2.fragments.TeamsFragment
import com.dmitri.projectapifootballv2.model.Leagues
import ru.terrakok.cicerone.android.support.SupportAppScreen

object AndroidScreen {

    class LeaguesScreens : SupportAppScreen() {
        override fun getFragment() = LeaguesFragment.newInstance()
    }

    class TeamsScreens(private val team: Leagues) : SupportAppScreen() {
        override fun getFragment() = TeamsFragment.newInstance(team)
    }
}