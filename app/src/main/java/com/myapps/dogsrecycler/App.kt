package com.myapps.dogsrecycler

import android.app.Application
import com.myapps.dogsrecycler.network.AnimalAPI
import com.myapps.dogsrecycler.network.RetrofitService
import retrofit2.Retrofit

class App : Application() {

    companion object {
        lateinit var INSTANCE: App
        private set
    }

    lateinit var retrofit: Retrofit

    override fun onCreate() {
        super.onCreate()
        INSTANCE = this
        retrofit = RetrofitService.getInstance()
    }

}