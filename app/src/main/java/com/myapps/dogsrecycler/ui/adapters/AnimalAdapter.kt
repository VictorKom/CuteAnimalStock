package com.myapps.dogsrecycler.ui.adapters

import android.graphics.drawable.Drawable
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.widget.ContentLoadingProgressBar
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.myapps.dogsrecycler.R
import com.myapps.dogsrecycler.utils.inflate

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
            val progressBar = this.cardView.findViewById<ContentLoadingProgressBar>(R.id.progressBar)
            progressBar.visibility = View.VISIBLE
            val image = this.cardView.findViewById<ImageView>(R.id.animal_image)
            Glide.with(cardView).load(imageURL).listener(object : RequestListener<Drawable> {
                override fun onLoadFailed(
                    e: GlideException?,
                    model: Any?,
                    target: Target<Drawable>?,
                    isFirstResource: Boolean
                ): Boolean {
                    progressBar.visibility = View.GONE
                    return false
                }

                override fun onResourceReady(
                    resource: Drawable?,
                    model: Any?,
                    target: Target<Drawable>?,
                    dataSource: DataSource?,
                    isFirstResource: Boolean
                ): Boolean {
                    progressBar.visibility = View.GONE
                    return false
                }
            }).centerCrop().into(image)
        }
    }

}