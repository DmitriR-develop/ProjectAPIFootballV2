package com.dmitri.projectapifootballv2.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.dmitri.projectapifootballv2.R.layout.fragment_teams
import com.dmitri.projectapifootballv2.abs.AbsFragment
import com.dmitri.projectapifootballv2.adapter.TeamsRVAdapter
import com.dmitri.projectapifootballv2.databinding.FragmentTeamsBinding
import com.dmitri.projectapifootballv2.model.Leagues
import com.dmitri.projectapifootballv2.navigation.IBackButtonListener
import com.dmitri.projectapifootballv2.presenter.TeamsPresenter
import com.dmitri.projectapifootballv2.view.TeamsItemView
import com.dmitri.projectapifootballv2.view.TeamsView
import moxy.ktx.moxyPresenter

class TeamsFragment : AbsFragment(fragment_teams), TeamsView, TeamsItemView, IBackButtonListener {
    companion object {
        private const val TEAM_NAME = "TEAM_NAME"

        @JvmStatic
        fun newInstance(team: Leagues) =
            TeamsFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(TEAM_NAME, team)
                }
            }
    }

    private var vb: FragmentTeamsBinding? = null
    private val binding get() = vb!!

    private val leagueId by lazy {
        arguments?.getInt(TEAM_NAME) ?: ""
    }

    private val presenter by moxyPresenter {
        TeamsPresenter(
            leagueId as Int,
            leaguesRepo,
            router,
            scheduler
        )
    }

    private var adapter: TeamsRVAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return FragmentTeamsBinding.inflate(inflater, container, false).also {
            vb = it
        }.root
    }

    override fun setName(league: String) {
        binding.tvChooseLeague.text = league
    }

    override var pos: Int
        get() = TODO("Not yet implemented")
        set(value) {}

    override fun init() {
        binding.rvTeams.layoutManager = LinearLayoutManager(context)
        adapter = TeamsRVAdapter(presenter.teamsListPresenter)
        binding.rvTeams.adapter = adapter
    }

    override fun updateList() {
        adapter?.notifyDataSetChanged()
    }

    override fun backPressed() = presenter.backPressed()

    override fun onDestroyView() {
        vb = null
        super.onDestroyView()
    }
}