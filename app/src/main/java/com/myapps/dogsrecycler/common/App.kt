package com.myapps.dogsrecycler.common

import android.app.Application
import com.myapps.dogsrecycler.api.AnimalAPI

class App : Application() {
    companion object {
        private var INSTANCE: AnimalAPI? = null
        fun getAnimalsAPI(): AnimalAPI {
            if (INSTANCE == null) {
                INSTANCE = AnimalAPI.create()
            }
            return INSTANCE!!
        }
    }
}