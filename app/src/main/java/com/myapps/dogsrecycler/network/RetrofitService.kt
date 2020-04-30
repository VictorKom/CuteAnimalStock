package com.myapps.dogsrecycler.network

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import rx.schedulers.Schedulers
import java.util.concurrent.TimeUnit

class RetrofitService {

    companion object {
        var animalAPI: AnimalAPI? = null
        private const val BASE_URL = "http://shibe.online/"

        fun getInstance(): AnimalAPI {
            if (animalAPI != null) {
                return animalAPI!!
            }

            val okHttpClient = OkHttpClient.Builder()
                .connectTimeout(120, TimeUnit.SECONDS)
                .writeTimeout(120, TimeUnit.SECONDS)
                .readTimeout(120, TimeUnit.SECONDS)
                .build()

            val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .client(okHttpClient)
                .build()
            animalAPI = retrofit.create(AnimalAPI::class.java)
            return animalAPI!!
        }
    }
}