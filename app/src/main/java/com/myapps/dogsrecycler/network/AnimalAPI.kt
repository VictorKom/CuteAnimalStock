package com.myapps.dogsrecycler.network


import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query



interface AnimalAPI {
    @GET("api/{animal}")
    fun getAnimals(@Path("animal") animal: String, @Query("count") count: Int) : Single<ArrayList<String>>

}