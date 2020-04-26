package com.myapps.dogsrecycler

import android.util.Log
import com.myapps.dogsrecycler.api.DogsApi
import rx.Observable

class DogsManager {

    fun getDogs() : Observable<ArrayList<String>> {
        return Observable.create {
            subscriber ->

                val api = DogsApi.create()
                val response = api.getDogs(10).execute()

                if (response.isSuccessful) {
                    subscriber.onNext(response.body())
                    subscriber.onCompleted()
                } else {
                    subscriber.onError(Throwable(response.message()))
                }
        }
    }
}