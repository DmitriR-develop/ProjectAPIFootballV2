package com.dmitri.projectapifootballv2.presenter

import com.dmitri.projectapifootball.navigation.AndroidScreen
import com.dmitri.projectapifootballv2.view.MainView
import com.github.terrakok.cicerone.Router
import moxy.MvpPresenter
import javax.inject.Inject

class MainPresenter : MvpPresenter<MainView>() {

    @Inject
    lateinit var router: Router

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        router.replaceScreen(AndroidScreen.LeaguesScreens().getFragment())
    }

    fun backClicked() {
        router.exit()
    }
}