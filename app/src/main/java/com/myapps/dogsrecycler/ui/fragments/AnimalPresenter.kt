package com.myapps.dogsrecycler.ui.fragments

import com.myapps.dogsrecycler.App
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import moxy.InjectViewState
import moxy.MvpPresenter


@InjectViewState
class AnimalPresenter : MvpPresenter<AnimalView>() {

    var animal: String = ""
    private var subscriptions = CompositeDisposable()

    fun requestAnimals() {
        val subscription = App.INSTANCE.animalAPI.getAnimals(animal, 5)
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