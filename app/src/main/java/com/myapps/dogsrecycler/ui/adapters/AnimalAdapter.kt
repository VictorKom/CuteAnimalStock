package com.myapps.dogsrecycler.ui.adapters

import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.myapps.dogsrecycler.R
import com.myapps.dogsrecycler.utils.inflate
import com.myapps.dogsrecycler.utils.loadImage
import kotlinx.android.synthetic.main.animals_item.view.*

class AnimalAdapter : ListAdapter<String, AnimalAdapter.ViewHolder>(ImageItemDiffCallback()) {

    private val imageList = ArrayList<String>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(parent.inflate(R.layout.animals_item))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.onBind(getItem(position))
    }

    fun addAnimals(animals: ArrayList<String>) {
        imageList.addAll(animals)
        submitList(imageList)
    }

    inner class ViewHolder(private val cardView: View) : RecyclerView.ViewHolder(cardView) {

        fun onBind(imageURL: String) {
            val progressBar = cardView.progressBar
            progressBar.visibility = View.VISIBLE
            val image = cardView.findViewById<ImageView>(R.id.animal_image)
            image.loadImage(imageURL, progressBar)
        }
    }

}