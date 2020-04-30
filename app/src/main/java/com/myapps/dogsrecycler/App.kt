package com.myapps.dogsrecycler

import android.app.Application
import com.myapps.dogsrecycler.network.AnimalAPI
import com.myapps.dogsrecycler.network.RetrofitService

class App : Application() {

    companion object {
        lateinit var INSTANCE: App
        private set
    }

    lateinit var animalAPI: AnimalAPI

    override fun onCreate() {
        super.onCreate()
        INSTANCE = this
        animalAPI = RetrofitService.getInstance()
    }

}