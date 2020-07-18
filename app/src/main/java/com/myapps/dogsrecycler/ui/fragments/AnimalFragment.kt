package com.myapps.dogsrecycler.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.myapps.dogsrecycler.R
import com.myapps.dogsrecycler.utils.InfiniteScrollListener
import com.myapps.dogsrecycler.utils.inflate
import com.myapps.dogsrecycler.ui.adapters.AnimalAdapter
import kotlinx.android.synthetic.main.fragment_animals.*
import moxy.MvpAppCompatFragment
import moxy.presenter.InjectPresenter

class AnimalFragment : MvpAppCompatFragment(), AnimalView {

    @InjectPresenter
    lateinit var animalPresenter: AnimalPresenter
    private val animalsAdapter by lazy { AnimalAdapter() }
    companion object {
        fun newInstance(animal: String) =
            AnimalFragment().apply {
                arguments = Bundle().apply {
                    putString("animal", animal)
                }
            }
    }

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
            addOnScrollListener(
                InfiniteScrollListener(
                    { requestAnimals() },
                    linearLayout
                )
            )
        }

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
        arguments?.getString("animal")?.let { animalPresenter.requestAnimals(it) }
    }
}
