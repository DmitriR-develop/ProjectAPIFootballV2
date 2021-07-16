package com.dmitri.projectapifootballv2.presenter

import com.dmitri.projectapifootballv2.model.entity.Teams
import com.dmitri.projectapifootballv2.model.repo.TeamsRepo
import com.dmitri.projectapifootballv2.view.TeamsItemView
import com.dmitri.projectapifootballv2.view.TeamsView
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.disposables.CompositeDisposable
import moxy.MvpPresenter
import ru.terrakok.cicerone.Router

class TeamsPresenter(
    private val leagueId: Int,
    private val teams: List<Teams>,
    private val teamsRepo: TeamsRepo,
    private val router: Router,
    private val scheduler: Scheduler
) : MvpPresenter<TeamsView>() {

    class TeamsListPresenter : com.dmitri.projectapifootballv2.presenter.TeamsListPresenter {
        val teams = mutableListOf<Teams>()
        override var itemClickListener: ((TeamsItemView) -> Unit)? = null
        override fun getCount() = teams.size
        override fun bindView(view: TeamsItemView) {
            Single.just(teams[view.pos]).subscribe({
                onBindViewSuccess(view, it)
            }, ::onBindViewError)
        }

        private fun onBindViewSuccess(view: TeamsItemView, teams: Teams) {
            view.setName(teams.name)
        }

        private fun onBindViewError(error: Throwable) {

        }
    }

    private val teamsListPresenter = TeamsListPresenter()
    private var disposable = CompositeDisposable()

    fun getTeamsListPresenter() = teamsListPresenter

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.init()
        loadData()
        teamsListPresenter.itemClickListener = {}
    }

    private fun loadData() {
        disposable.add(
            teamsRepo.getTeams(leagueId, teams)
                .observeOn(scheduler)
                .subscribe({ teams -> subscribeLeagues(teams) }, { it.printStackTrace() })
        )
    }

    private fun subscribeLeagues(teams: List<Teams>) {
        teamsListPresenter.teams.clear()
        teamsListPresenter.teams.addAll(teams)
        viewState.updateList()
    }

    fun backPressed(): Boolean {
        router.exit()
        return true
    }

    override fun onDestroy() {
        disposable.dispose()
        super.onDestroy()
    }
}