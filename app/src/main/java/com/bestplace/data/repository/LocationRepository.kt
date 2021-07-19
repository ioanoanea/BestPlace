package com.bestplace.data.repository

import com.bestplace.data.model.Location

class LocationRepository(
    override val collection: String = "locations",
) : Repository<Location>() {

    val stringItems: MutableList<String> = mutableListOf()

    // Get all locations from Firestore
    override fun getAll() {
        getCollection().get()
            .addOnSuccessListener { documents ->
                if (documents != null) {
                    // clear old items
                    items.clear()
                    // get each location
                    for (document in documents) {
                        val latitude = "${document.data["latitude"]}".toLong()
                        val longitude = "${document.data["latitude"]}".toLong()
                        items.add(Location(latitude as Long, longitude as Long))
                        // stringItems.add("${document.data["latitude"]}")
                    }
                    // notify item updated
                    this.onUpdateListener?.invoke()
                }
            }
            .addOnFailureListener { exception ->
                throw exception
            }
    }
    
}