package com.myapps.dogsrecycler.ui.adapters

import android.support.v7.widget.CardView
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import com.myapps.dogsrecycler.R
import com.squareup.picasso.Picasso

class AnimalAdapter : RecyclerView.Adapter<AnimalAdapter.ViewHolder>() {

    var imagesURL = ArrayList<String>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val cardView = LayoutInflater.from(parent.context)
            .inflate(R.layout.animals_item, parent,false) as CardView
        return ViewHolder(cardView)
    }

    override fun getItemCount(): Int {
        return imagesURL.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.onBind(position)
    }

    fun addAnimals(animals: ArrayList<String>) {
        imagesURL.addAll(animals)
        notifyDataSetChanged()
    }

    inner class ViewHolder(private val cardView: CardView) : RecyclerView.ViewHolder(cardView) {
        fun onBind(position: Int) {
            val image = this.cardView.findViewById<ImageView>(R.id.animal_image)
            Picasso.get().load(imagesURL[position]).fit().centerCrop().into(image)
        }
    }

}