package com.bestplace.data.repository
import com.bestplace.data.model.Place
import java.util.concurrent.Executor

class PlaceRepository(executor: Executor) : FirebaseRepository("places", executor) {

    /**
     * Search places by name
     * @param str String
     * @param callback Function1<Result<MutableList<Place>>, Unit>
     */
    fun searchByName(str: String, callback: (Result<MutableList<Place>>) -> Unit) {
        executor.execute {
            // search string in name field
            getCollectionReference()
                .whereGreaterThanOrEqualTo("name", str)
                .whereLessThanOrEqualTo("name", str + "zzzzzzz")
                .get()
                .addOnSuccessListener { result ->
                    if (result.size() != 0) {
                        // create items list
                        val items = mutableListOf<Place>()
                        // get documents details
                        for (document in result) {
                            val locationId = "${document.data["location_id"]}"
                            val name = "${document.data["name"]}"
                            val description = "${document.data["description"]}"
                            val category = "${document.data["category"]}"

                            // add new place to item list
                            val place = Place(locationId, name, description, category)
                            items.add(place)
                        }
                        callback(Result.Success(items))
                    } else {
                        // return null if nothing was found
                        callback(Result.Success(null))
                    }
                }
                .addOnFailureListener {
                    callback(Result.Error(it))
                }
        }
    }

    /**
     * Search places by category
     * @param str String
     * @param callback Function1<Result<MutableList<Place>>, Unit>
     */
    fun searchByCategory(str: String, callback: (Result<MutableList<Place>>) -> Unit) {
        executor.execute {
            // search string in category field
            getCollectionReference()
                .whereGreaterThanOrEqualTo("category", str)
                .whereLessThanOrEqualTo("category", str + "zzzzzzz")
                .get()
                .addOnSuccessListener { result ->
                    if (result.size() != 0) {
                        // create items list
                        val items = mutableListOf<Place>()
                        // get documents details
                        for (document in result) {
                            val locationId = "${document.data["location_id"]}"
                            val name = "${document.data["name"]}"
                            val description = "${document.data["description"]}"
                            val category = "${document.data["category"]}"

                            // add new place to item list
                            val place = Place(locationId, name, description, category)
                            items.add(place)
                        }
                        callback(Result.Success(items))
                    } else {
                        // return null if nothing was found
                        callback(Result.Success(null))
                    }
                }
                .addOnFailureListener {
                    callback(Result.Error(it))
                }
        }
    }
}