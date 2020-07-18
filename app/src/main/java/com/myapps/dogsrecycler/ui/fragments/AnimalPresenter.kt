package com.myapps.dogsrecycler.ui.fragments

import com.myapps.dogsrecycler.data.RemoteRepository
import io.reactivex.disposables.CompositeDisposable
import moxy.InjectViewState
import moxy.MvpPresenter

@InjectViewState
class AnimalPresenter : MvpPresenter<AnimalView>() {
    private val remoteRepository = RemoteRepository.getInstance()
    private var disposables = CompositeDisposable()

    fun requestAnimals(animal: String) {
        val disposable = remoteRepository.getAnimals(animal)
            .subscribe(
                { retrievedAnimals ->
                    viewState.addAnimals(retrievedAnimals)
                },
                { e ->
                    viewState.showErrorMassage(e.message)
                })
        disposables.add(disposable)
    }

    override fun onDestroy() {
        super.onDestroy()
        disposables.clear()
    }
}