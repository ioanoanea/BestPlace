package com.bestplace.ui.viewHolders

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bestplace.R

class CategoryViewHolder(view: View): RecyclerView.ViewHolder(view) {

    val categoryTitle: TextView = view.findViewById(R.id.category_title)
    val categoryIcon: ImageView = view.findViewById(R.id.category_icon)

}