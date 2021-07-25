package com.bestplace.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bestplace.R
import com.bestplace.ui.viewHolders.CategoriesViewHolder

class CategoriesAdapter(private val items: Array<Item>): RecyclerView.Adapter<CategoriesViewHolder>() {

    // item class
    data class Item (
        val icon: Int,
        val title: String
    )

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoriesViewHolder {
        // Create a new view, which defines the UI of the list item
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.category_card, parent, false)
        view.layoutParams = ViewGroup.LayoutParams(parent.width / 2, parent.width / 2)
        return CategoriesViewHolder(view)
    }

    override fun onBindViewHolder(holder: CategoriesViewHolder, position: Int) {
        holder.categoryTitle.text = items[position].title
        holder.categoryIcon.setImageResource(items[position].icon)
    }

    override fun getItemCount(): Int {
        return items.size
    }

}