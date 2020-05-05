package com.myapps.dogsrecycler.ui.fragments

import moxy.MvpView
import moxy.viewstate.strategy.AddToEndStrategy
import moxy.viewstate.strategy.SkipStrategy
import moxy.viewstate.strategy.StateStrategyType


@StateStrategyType(AddToEndStrategy::class)
interface AnimalView : MvpView {

    fun addAnimals(animals: ArrayList<String>)

    @StateStrategyType(SkipStrategy::class)
    fun showErrorMassage(message: String?)


}