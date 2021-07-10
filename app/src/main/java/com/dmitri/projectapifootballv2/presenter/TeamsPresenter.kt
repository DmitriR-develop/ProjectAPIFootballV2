package com.dmitri.projectapifootballv2.presenter

import com.dmitri.projectapifootballv2.model.ILeaguesRepo
import com.dmitri.projectapifootballv2.model.Teams
import com.dmitri.projectapifootballv2.view.TeamsItemView
import com.dmitri.projectapifootballv2.view.TeamsView
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.disposables.CompositeDisposable
import moxy.MvpPresenter
import ru.terrakok.cicerone.Router

class TeamsPresenter(
    private val leagueId: Int,
    private val leaguesRepo: ILeaguesRepo,
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

    val teamsListPresenter = TeamsListPresenter()
    private var disposable = CompositeDisposable()

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.init()
        loadData()
        teamsListPresenter.itemClickListener = {}
    }

    private fun loadData() {
        disposable.add(
            leaguesRepo.getTeams(leagueId)
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