package com.bestplace.data.repository
import com.bestplace.data.model.Place
import java.util.concurrent.Executor

class PlaceRepository(executor: Executor) : FirebaseRepository("places", executor) {

    /**
     * Returns place with a
     * @param strId String
     * @param callback Function1<Result<Place>, Unit>
     */
    fun getById(strId: String, callback: (Result<Place>) -> Unit) {
        // execute query in background
        executor.execute {
            getCollectionReference()
                .document(strId)
                .get()
                .addOnSuccessListener { result ->
                    if (result.exists()) {
                        // get place data
                        val id = result.id
                        val name = "${result.get("name")}"
                        val category = "${result.get("category")}"
                        val description = "${result.get("description")}"
                        val address = "${result.get("address")}"
                        val latitude = "${result.get("latitude")}".toDouble()
                        val longitude = "${result.get("longitude")}".toDouble()
                        val picture = "${result.get("picture")}"

                        val place = Place(id, latitude, longitude, name, description, category, address, picture)
                        // callback success result
                        callback(Result.Success(place))
                    } else {
                        // callback null result
                        callback(Result.Success(null))
                    }
                }
                .addOnFailureListener {
                    callback(Result.Error(it))
                }
        }
    }

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
                            // get place data
                            val id = document.id
                            val latitude = "${document.data["latitude"]}".toDouble()
                            val longitude = "${document.data["longitude"]}".toDouble()
                            val name = "${document.data["name"]}"
                            val description = "${document.data["description"]}"
                            val category = "${document.data["category"]}"
                            val address = "${document.data["address"]}"
                            val picture = "${document.data["picture"]}"

                            // add new place to item list
                            val place = Place(id, latitude, longitude, name, description, category, address, picture)
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
     * Get places by category
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
                            // get place data
                            val id = document.id
                            val latitude = "${document.data["latitude"]}".toDouble()
                            val longitude = "${document.data["longitude"]}".toDouble()
                            val name = "${document.data["name"]}"
                            val description = "${document.data["description"]}"
                            val category = "${document.data["category"]}"
                            val address = "${document.data["address"]}"
                            val picture = "${document.data["picture"]}"

                            // add new place to item list
                            val place = Place(id, latitude, longitude, name, description, category, address, picture)
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