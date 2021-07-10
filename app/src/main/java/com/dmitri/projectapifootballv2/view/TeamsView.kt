package com.dmitri.projectapifootballv2.view

import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(AddToEndSingleStrategy::class)
interface TeamsView : MvpView {
    fun init()
    fun updateList()
}