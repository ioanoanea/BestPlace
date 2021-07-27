package com.bestplace.ui.viewHolders

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bestplace.R

class NotFoundViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    val text: TextView = itemView.findViewById(R.id.text)
    val image: ImageView = itemView.findViewById(R.id.image)
}