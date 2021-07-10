package com.dmitri.projectapifootballv2.presenter

import com.dmitri.projectapifootball.model.ILeaguesRepo
import com.dmitri.projectapifootball.model.Leagues
import com.dmitri.projectapifootball.navigation.AndroidScreen
import com.dmitri.projectapifootballv2.view.LeaguesItemView
import com.dmitri.projectapifootballv2.view.LeaguesView
import com.github.terrakok.cicerone.Router
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.disposables.CompositeDisposable
import moxy.MvpPresenter

class LeaguesPresenter(
    private val leaguesRepo: ILeaguesRepo,
    private val router: Router,
    private val scheduler: Scheduler
) : MvpPresenter<LeaguesView>() {

    class LeaguesListPresenter : com.dmitri.projectapifootball.presenter.LeaguesListPresenter {
        val leagues = mutableListOf<Leagues>()
        override var itemClickListener: ((LeaguesItemView) -> Unit)? = null
        override fun getCount() = leagues.size
        override fun bindView(view: LeaguesItemView) {
            Single.just(leagues[view.pos]).subscribe({
                onBindViewSuccess(view, it)
            }, ::onBindViewError)
        }

        private fun onBindViewSuccess(view: LeaguesItemView, leagues: Leagues) {
            view.setId(leagues.id)
            view.setName(leagues.name)
            view.setCountry(leagues.country)
            view.setCode(leagues.code)
            view.setSeason(leagues.season)
            view.setTeam(leagues.team)
            view.setType(leagues.type)
            view.setCurrent(leagues.current)
            view.setSearch(leagues.search)
            view.setLast(leagues.last)
        }

        private fun onBindViewError(error: Throwable) {

        }
    }

    val leaguesListPresenter = LeaguesListPresenter()
    private var disposable: CompositeDisposable = CompositeDisposable()

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.init()
        loadData()
        leaguesListPresenter.itemClickListener = { itemView ->
            val league = leaguesListPresenter.leagues[itemView.pos]
            router.navigateTo(AndroidScreen.TeamsScreens(league).getFragment())
        }
    }

    private fun loadData() {
        disposable.add(
            leaguesRepo.getLeagues()
                .observeOn(scheduler)
                .subscribe({ leagues -> subscribeLeagues(leagues) }, { it.printStackTrace() })
        )
    }

    private fun subscribeLeagues(leagues: List<Leagues>) {
        leaguesListPresenter.leagues.clear()
        leaguesListPresenter.leagues.addAll(leagues)
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