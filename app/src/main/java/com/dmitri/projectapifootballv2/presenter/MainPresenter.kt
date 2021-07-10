package com.dmitri.projectapifootballv2.presenter

import com.dmitri.projectapifootballv2.navigation.AndroidScreen
import com.dmitri.projectapifootballv2.view.MainView
import moxy.MvpPresenter
import ru.terrakok.cicerone.Router

class MainPresenter(private val router: Router) : MvpPresenter<MainView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        router.replaceScreen(AndroidScreen.LeaguesScreens())
    }

    fun backClicked() {
        router.exit()
    }
}