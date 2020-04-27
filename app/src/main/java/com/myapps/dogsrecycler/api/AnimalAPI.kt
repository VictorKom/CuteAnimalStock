package com.myapps.dogsrecycler.api

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface AnimalAPI {
    @GET("api/{animal}")
    fun getDogs(@Path("animal") animal: String, @Query("count") count: Int) : Call<ArrayList<String>>

    companion object Factory {
        fun create(): AnimalAPI {
            val retrofit = Retrofit.Builder()
                .baseUrl("http://shibe.online/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            return retrofit.create(AnimalAPI::class.java)
        }
    }
}