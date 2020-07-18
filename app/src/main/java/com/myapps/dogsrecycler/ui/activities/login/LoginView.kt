package com.myapps.dogsrecycler.ui.activities.login

import moxy.MvpView
import moxy.viewstate.strategy.SkipStrategy
import moxy.viewstate.strategy.StateStrategyType


@StateStrategyType(SkipStrategy::class)
interface LoginView : MvpView {
    fun successLogIn()
}