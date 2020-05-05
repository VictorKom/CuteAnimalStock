package com.myapps.dogsrecycler.ui.fragments

import com.myapps.dogsrecycler.App
import moxy.InjectViewState
import moxy.MvpPresenter
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import rx.subscriptions.CompositeSubscription

@InjectViewState
class AnimalPresenter : MvpPresenter<AnimalView>() {

    var animal: String = ""
    private var subscriptions = CompositeSubscription()

    fun requestAnimals() {
        val subscription = App.INSTANCE.animalAPI.getDogs(animal, 5)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { retrievedAnimals ->
                    viewState.addAnimals(retrievedAnimals)
                },
                { e ->
                    viewState.showErrorMassage(e.message)
                })
        subscriptions.add(subscription)
    }

    override fun onDestroy() {
        super.onDestroy()
        subscriptions.clear()
    }
}