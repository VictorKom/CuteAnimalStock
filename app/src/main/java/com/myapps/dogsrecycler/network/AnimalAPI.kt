package com.myapps.dogsrecycler.network

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import rx.Observable

interface AnimalAPI {
    @GET("api/{animal}")
    fun getDogs(@Path("animal") animal: String, @Query("count") count: Int) : Observable<ArrayList<String>>

}