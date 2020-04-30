package com.myapps.dogsrecycler.ui.fragments

import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.AddToEndStrategy
import com.arellomobile.mvp.viewstate.strategy.SkipStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType

@StateStrategyType(AddToEndStrategy::class)
interface AnimalView : MvpView {

    fun addAnimals(animals: ArrayList<String>)

    @StateStrategyType(SkipStrategy::class)
    fun showErrorMassage(message: String?)


}