package com.myapps.dogsrecycler

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.myapps.dogsrecycler.common.InfiniteScrollListener
import com.myapps.dogsrecycler.common.inflate
import kotlinx.android.synthetic.main.fragment_animals.*
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import rx.subscriptions.CompositeSubscription

class AnimalFragment : Fragment() {

    var animal: String = ""
    private val animalsAdapter by lazy { AnimalAdapter() }
    private val animalsManager by lazy { AnimalManager(animal) }
    private var subscriptions = CompositeSubscription()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return container?.inflate(R.layout.fragment_animals)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val linearLayout = LinearLayoutManager(activity)
        animalsList.apply {
            adapter = animalsAdapter
            setHasFixedSize(true)
            layoutManager = linearLayout
            clearOnScrollListeners()
            addOnScrollListener( InfiniteScrollListener( {requestAnimals()}, linearLayout))
        }

        if (savedInstanceState != null){
            val imagesURL = savedInstanceState.getStringArrayList("imagesURL") as ArrayList
            animalsAdapter.addAnimals(imagesURL)
        } else {
            requestAnimals()
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putStringArrayList("imagesURL", animalsAdapter.imagesURL)
    }

    override fun onResume() {
        super.onResume()
        subscriptions = CompositeSubscription()
    }

    override fun onPause() {
        super.onPause()
        subscriptions.clear()
    }

    private fun requestAnimals() {
        val subscription = animalsManager.getDogs()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { retrievedAnimals ->
                   animalsAdapter.addAnimals(retrievedAnimals)
                },
                {   e ->
                    Snackbar.make(animalsList, e.message ?: "", Snackbar.LENGTH_LONG).show()
                })
        subscriptions.add(subscription)
    }
}
