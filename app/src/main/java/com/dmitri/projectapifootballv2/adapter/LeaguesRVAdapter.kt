package com.dmitri.projectapifootballv2.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dmitri.projectapifootballv2.databinding.ItemLeagueBinding
import com.dmitri.projectapifootballv2.presenter.LeaguesPresenter
import com.dmitri.projectapifootballv2.view.LeaguesItemView

class LeaguesRVAdapter(val presenter: LeaguesPresenter.LeaguesListPresenter) :
    RecyclerView.Adapter<LeaguesRVAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        ItemLeagueBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )
        .apply {
            itemView.setOnClickListener {
                presenter.itemClickListener?.invoke(this)
            }
        }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        presenter.bindView(holder.apply { pos = position })

    override fun getItemCount() = presenter.getCount()
    inner class ViewHolder(private val vb: ItemLeagueBinding) : RecyclerView.ViewHolder(vb.root),
        LeaguesItemView {

        override var pos = -1

        override fun setName(text: String) {
            vb.tvLeagueName.text = text
        }
    }
}