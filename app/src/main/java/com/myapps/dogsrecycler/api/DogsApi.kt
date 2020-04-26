package com.myapps.dogsrecycler.api

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface DogsApi {
    @GET("api/shibes")
    fun getDogs(@Query("count") count: Int) : Call<ArrayList<String>>

    companion object Factory {
        fun create(): DogsApi {
            val retrofit = Retrofit.Builder()
                .baseUrl("http://shibe.online/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            return retrofit.create(DogsApi::class.java)
        }
    }
}