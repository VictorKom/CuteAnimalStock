package com.myapps.dogsrecycler

import android.support.v4.content.ContextCompat
import android.support.v7.widget.CardView
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso
import com.squareup.picasso.RequestCreator


class DogsAdapter() : RecyclerView.Adapter<DogsAdapter.MyViewHolder>() {

    var imagesURL = ArrayList<String>()

    inner class MyViewHolder(val cardView: CardView) : RecyclerView.ViewHolder(cardView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val cardView = LayoutInflater.from(parent.context)
            .inflate(R.layout.dogs_item, parent,false) as CardView
        return MyViewHolder(cardView)
    }

    override fun getItemCount(): Int {
        return imagesURL.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val image = holder.cardView.findViewById<ImageView>(R.id.dog_image)
        Picasso.get().load(imagesURL[position]).fit().centerCrop().into(image)
    }

    fun addDogs(dogs: ArrayList<String>) {
        imagesURL.addAll(dogs)
    }


}