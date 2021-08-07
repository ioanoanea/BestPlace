package com.bestplace.ui.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bestplace.R
import com.bestplace.data.model.Place
import com.bestplace.ui.viewHolders.PlaceViewHolder

class PlacesAdapter(private var items: MutableList<Item>): RecyclerView.Adapter<PlaceViewHolder>() {

    // item class
    data class Item(
        val place: Place,
        val onClick: (() -> Unit)? = null
    );

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlaceViewHolder {
        // Create a new view, which defines the UI of the list item
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.place_card, parent, false)
        return PlaceViewHolder(view)
    }

    override fun onBindViewHolder(holder: PlaceViewHolder, position: Int) {
        holder.title.text = items[position].place.name
        holder.description.text = items[position].place.description
        holder.itemView.setOnClickListener {
            items[position].onClick?.invoke()
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    /**
     * Update items list
     * @param items MutableList<Item>
     */
    @SuppressLint("NotifyDataSetChanged")
    fun updateItems(items: MutableList<Item>) {
        this.items = items
        this.notifyDataSetChanged()
    }
}