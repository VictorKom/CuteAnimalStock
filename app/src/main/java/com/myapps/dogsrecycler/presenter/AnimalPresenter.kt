package com.myapps.dogsrecycler.presenter

import android.support.design.widget.Snackbar
import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.myapps.dogsrecycler.model.AnimalManager
import com.myapps.dogsrecycler.view.AnimalView
import kotlinx.android.synthetic.main.fragment_animals.*
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import rx.subscriptions.CompositeSubscription

@InjectViewState
class AnimalPresenter : MvpPresenter<AnimalView>() {

    var animal: String = ""
    private var subscriptions = CompositeSubscription()
    private val animalsManager by lazy { AnimalManager(animal) }

    fun requestAnimals() {
        val subscription = animalsManager.getDogs()
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