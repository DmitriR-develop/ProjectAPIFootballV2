package com.dmitri.projectapifootballv2.presenter

import com.dmitri.projectapifootballv2.view.IItemView

interface IListPresenter<V : IItemView> {
    var itemClickListener: ((V) -> Unit)?
    fun bindView(View: V)
    fun getCount(): Int
}