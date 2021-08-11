package com.bestplace.ui.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bestplace.R
import com.bestplace.data.model.Location
import com.bestplace.data.model.Place
import com.bestplace.ui.viewHolders.PlaceViewHolder
import com.bumptech.glide.Glide

class PlacesAdapter(
    private val context: Context? = null,
    private var items: MutableList<Item>
    ): RecyclerView.Adapter<PlaceViewHolder>() {

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
        // load texts
        holder.title.text = items[position].place.name
        holder.description.text = items[position].place.description
        holder.address.text = items[position].place.address
        holder.distance.text = items[position].place.getDistance(Location(1.32432, 5.4546)).toString()
        // load header image
        if (context != null) {
            Glide.with(context)
                .load(items[position].place.picture)
                .centerCrop()
                .into(holder.image)
        }
        // set on click listener
        holder.itemView.setOnClickListener {
            items[position].onClick?.invoke()
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    /**
     * Add items to item's list
     * @param item Item
     */
    fun addItem(item: Item) {
        this.items.add(item)
        this.notifyItemInserted(this.items.size)
    }

    /**
     * Clear all items
     */
    fun clearItems() {
        this.items.clear()
    }
}