package com.bestplace.ui.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
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
        var location: Location? = null,
        val onClick: (() -> Unit)? = null
    );

    // location position map - used to found location position
    private val locationPositionMap = hashMapOf<String, Int>()

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
        holder.distance.text = items[position].location?.getDistance(Location(1.3543, 3.545)).toString()
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
        this.locationPositionMap[item.place.locationId] = this.items.size - 1
        this.notifyItemInserted(this.items.size)
    }

    /**
     * clear all items
     */
    fun clearItems() {
        this.items.clear()
        this.locationPositionMap.clear()
    }

    /**
     * Set item location
     * @param locationId String
     * @param location Location
     */
    fun setLocation(locationId: String, location: Location) {
        val position = locationPositionMap[locationId]
        //this.items[position].location = location
        //this.notifyItemChanged(position)
        Toast.makeText(context, "position: $position \n" +
                "latitude: ${location.latitude} \n" +
                "longitufr: ${location.longitude} \n", Toast.LENGTH_SHORT).show()
    }

}