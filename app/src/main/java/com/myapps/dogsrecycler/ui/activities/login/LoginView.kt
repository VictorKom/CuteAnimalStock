package com.myapps.dogsrecycler.ui.activities.login

import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.SkipStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType

@StateStrategyType(SkipStrategy::class)
interface LoginView : MvpView{
    fun successLogIn()
}