package com.myapps.dogsrecycler.ui.fragments

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.arellomobile.mvp.MvpAppCompatFragment
import com.arellomobile.mvp.presenter.InjectPresenter
import com.myapps.dogsrecycler.R
import com.myapps.dogsrecycler.common.InfiniteScrollListener
import com.myapps.dogsrecycler.common.inflate
import com.myapps.dogsrecycler.ui.adapters.AnimalAdapter
import kotlinx.android.synthetic.main.fragment_animals.*


class AnimalFragment : MvpAppCompatFragment(),
    AnimalView {

    @InjectPresenter
    lateinit var animalPresenter: AnimalPresenter

    var animal: String = ""
    private val animalsAdapter by lazy { AnimalAdapter() }

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
            addOnScrollListener(InfiniteScrollListener({ requestAnimals() }, linearLayout))
        }
        animalPresenter.animal = animal

        if (savedInstanceState == null) {
            requestAnimals()
        }
    }

    override fun addAnimals(animals: ArrayList<String>) {
        animalsAdapter.addAnimals(animals)
    }

    override fun showErrorMassage(message: String?) {
        Snackbar.make(animalsList, message ?: "", Snackbar.LENGTH_LONG).show()
    }

    private fun requestAnimals() {
        animalPresenter.requestAnimals()
    }
}
