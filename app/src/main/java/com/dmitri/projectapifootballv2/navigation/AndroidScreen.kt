package com.dmitri.projectapifootball.navigation

import com.dmitri.projectapifootball.fragments.LeaguesFragment
import com.dmitri.projectapifootball.fragments.TeamsFragment
import com.dmitri.projectapifootball.model.Leagues
import com.dmitri.projectapifootballv2.navigation.IScreens
import com.github.terrakok.cicerone.Screen
import com.github.terrakok.cicerone.androidx.FragmentScreen

object AndroidScreen {

    class LeaguesScreens : IScreens {
        override fun getFragment(): Screen = FragmentScreen { LeaguesFragment.newInstance() }
    }

    class TeamsScreens(private val team: Leagues) : IScreens {
        override fun getFragment(): Screen = FragmentScreen { TeamsFragment.newInstance(team) }
    }
}