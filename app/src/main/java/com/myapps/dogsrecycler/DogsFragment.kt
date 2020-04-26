package com.myapps.dogsrecycler

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.myapps.dogsrecycler.common.InfiniteScrollListener
import com.myapps.dogsrecycler.common.inflate
import kotlinx.android.synthetic.main.fragment_dogs.*
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import rx.subscriptions.CompositeSubscription

class DogsFragment : Fragment() {

    private val dogsList by lazy { dogs_list }
    private val dogsManager by lazy { DogsManager() }
    private var subscriptions = CompositeSubscription()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return container?.inflate(R.layout.fragment_dogs)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        dogsList.setHasFixedSize(true)
        val gridLayout = GridLayoutManager(activity, 2)
        dogsList.layoutManager = gridLayout
        dogsList.clearOnScrollListeners()
        dogsList.addOnScrollListener( InfiniteScrollListener( {requestDogs()}, gridLayout))

        initAdapter()

        if (savedInstanceState != null){
            val imagesURL = savedInstanceState.getStringArrayList("imagesURL") as ArrayList
            (dogsList.adapter as DogsAdapter).addDogs(imagesURL)
        } else {
            requestDogs()
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putStringArrayList("imagesURL", (dogsList.adapter as DogsAdapter).imagesURL)
    }

    override fun onResume() {
        super.onResume()
        subscriptions = CompositeSubscription()
    }

    override fun onPause() {
        super.onPause()
        subscriptions.clear()
    }

    private fun initAdapter() {
        if (dogsList.adapter == null){
            dogsList.adapter = DogsAdapter()
        }
    }

    private fun requestDogs() {
        val subscription = dogsManager.getDogs()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { retrievedDogs ->
                    (dogsList.adapter as DogsAdapter).addDogs(retrievedDogs)
                    (dogsList.adapter as DogsAdapter).notifyDataSetChanged()
                },
                {   e ->
                    Snackbar.make(dogsList, e.message ?: "", Snackbar.LENGTH_LONG).show()
                })
        subscriptions.add(subscription)
    }
}
