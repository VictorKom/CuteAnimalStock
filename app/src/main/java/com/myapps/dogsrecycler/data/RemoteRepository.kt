package com.myapps.dogsrecycler.data

import com.myapps.dogsrecycler.App
import com.myapps.dogsrecycler.network.AnimalAPI
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class RemoteRepository {

    companion object {
        private var INSTANCE: RemoteRepository? = null
        fun getInstance() : RemoteRepository {
            if (INSTANCE == null){
                INSTANCE = RemoteRepository()
            }
            return INSTANCE!!
        }
    }
    private val retrofit = App.INSTANCE.retrofit
    private val animalAPI = retrofit.create(AnimalAPI::class.java)

    fun getAnimals(animal: String, count: Int = 5) : Single<ArrayList<String>> {
        return animalAPI.getAnimals(animal, count)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }
}