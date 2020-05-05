package com.myapps.dogsrecycler.network

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import rx.Single


interface AnimalAPI {
    @GET("api/{animal}")
    fun getDogs(@Path("animal") animal: String, @Query("count") count: Int) : Single<ArrayList<String>>

}