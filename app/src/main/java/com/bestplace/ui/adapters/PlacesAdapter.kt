package com.bestplace.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bestplace.R
import com.bestplace.data.model.Place
import com.bestplace.ui.viewHolders.NotFoundViewHolder
import com.bestplace.ui.viewHolders.PlaceViewHolder

class PlacesAdapter(private val items: Array<Place>):
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        const val VIEW_TYPE_ONE = 1
        const val VIEW_TYPE_TWO = 2
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == VIEW_TYPE_ONE) {
            PlaceViewHolder(
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.place_card, parent, false)
            )
        } else {
            NotFoundViewHolder(
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.not_found_card, parent, false)
            )
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (getItemViewType(position) == VIEW_TYPE_ONE) {
            (holder as PlaceViewHolder).title.text = items[position].name
            (holder as PlaceViewHolder).description.text = items[position].description
        } else {
            (holder as NotFoundViewHolder)
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun getItemViewType(position: Int): Int {
        return if (items.isEmpty()) {
            VIEW_TYPE_TWO
        } else {
            VIEW_TYPE_ONE
        }
    }
}