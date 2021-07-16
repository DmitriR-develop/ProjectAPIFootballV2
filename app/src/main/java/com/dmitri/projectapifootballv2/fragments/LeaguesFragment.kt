package com.dmitri.projectapifootballv2.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.dmitri.projectapifootballv2.R.layout.fragment_leagues
import com.dmitri.projectapifootballv2.abs.AbsFragment
import com.dmitri.projectapifootballv2.adapter.LeaguesRVAdapter
import com.dmitri.projectapifootballv2.databinding.FragmentLeaguesBinding
import com.dmitri.projectapifootballv2.navigation.IBackButtonListener
import com.dmitri.projectapifootballv2.presenter.LeaguesPresenter
import com.dmitri.projectapifootballv2.view.LeaguesView
import moxy.ktx.moxyPresenter

class LeaguesFragment : AbsFragment(fragment_leagues), LeaguesView, IBackButtonListener {

    companion object {
        fun newInstance() = LeaguesFragment()
    }

    val presenter by moxyPresenter {
        LeaguesPresenter(leaguesRepo, router, scheduler, networkStatus)
    }

    var adapter: LeaguesRVAdapter? = null

    private var vb: FragmentLeaguesBinding? = null
    private val binding get() = vb!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return FragmentLeaguesBinding.inflate(inflater, container, false)
            .also { vb = it }.root
    }

    override fun backPressed() = presenter.backPressed()

    override fun init() {
        vb?.rvLeagues?.layoutManager = LinearLayoutManager(context)
        adapter = LeaguesRVAdapter(presenter.getLeaguesListPresenter())
        binding.rvLeagues.adapter = adapter
    }

    override fun updateList() {
        adapter?.notifyDataSetChanged()
    }

    override fun onDestroyView() {
        vb = null
        super.onDestroyView()
    }
}