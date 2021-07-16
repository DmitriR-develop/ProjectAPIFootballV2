package com.dmitri.projectapifootballv2.presenter

import com.dmitri.projectapifootballv2.model.entity.Leagues
import com.dmitri.projectapifootballv2.model.repo.LeaguesRepo
import com.dmitri.projectapifootballv2.navigation.AndroidScreen
import com.dmitri.projectapifootballv2.network.NetworkStatus
import com.dmitri.projectapifootballv2.view.LeaguesItemView
import com.dmitri.projectapifootballv2.view.LeaguesView
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.disposables.CompositeDisposable
import moxy.MvpPresenter
import ru.terrakok.cicerone.Router

class LeaguesPresenter(
    private val leaguesRepo: LeaguesRepo,
    private val router: Router,
    private val scheduler: Scheduler,
    private val networkStatus: NetworkStatus
) : MvpPresenter<LeaguesView>() {

    class LeaguesListPresenter : com.dmitri.projectapifootballv2.presenter.LeaguesListPresenter {
        val leagues = mutableListOf<Leagues>()
        override var itemClickListener: ((LeaguesItemView) -> Unit)? = null
        override fun getCount() = leagues.size
        override fun bindView(view: LeaguesItemView) {
            Single.just(leagues[view.pos]).subscribe({
                onBindViewSuccess(view, it)
            }, ::onBindViewError)
        }

        private fun onBindViewSuccess(view: LeaguesItemView, leagues: Leagues) {
            view.setName(leagues.id)
        }

        private fun onBindViewError(error: Throwable) {

        }
    }

    private val leaguesListPresenter = LeaguesListPresenter()
    private var disposable: CompositeDisposable = CompositeDisposable()

    fun getLeaguesListPresenter() = leaguesListPresenter

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.init()
        loadData()
        leaguesListPresenter.itemClickListener = { itemView ->
            val league = leaguesListPresenter.leagues[itemView.pos]
            router.navigateTo(AndroidScreen.TeamsScreens(league))
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