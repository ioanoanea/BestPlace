package com.bestplace.ui.viewHolders

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bestplace.R

class CategoriesViewHolder(view: View): RecyclerView.ViewHolder(view) {

    val categoryTitle: TextView
    val categoryIcon: ImageView

    init {
        this.categoryTitle = view.findViewById(R.id.category_title)
        this.categoryIcon = view.findViewById(R.id.category_icon)
    }

}