package com.bestplace.ui.viewHolders

import android.view.View
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bestplace.R

class PlaceViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    val image: ImageView = itemView.findViewById(R.id.image)
    val ratingBar: RatingBar = itemView.findViewById(R.id.rating_bar)
    val title: TextView = itemView.findViewById(R.id.title)
    val description: TextView = itemView.findViewById(R.id.description)
    val address: TextView = itemView.findViewById(R.id.address)
    val distance: TextView = itemView.findViewById(R.id.distance)
}