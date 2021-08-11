package com.bestplace.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bestplace.R
import com.bestplace.data.model.Location
import com.bestplace.data.model.Place
import com.bestplace.data.viewModel.LocationViewModel
import com.bestplace.data.viewModel.PlaceListViewModel
import com.bestplace.ui.activities.HomeScreenActivity
import com.bestplace.ui.adapters.PlacesAdapter


class PlacesFragment: Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var placesAdapter: PlacesAdapter
    private val placeListViewModel: PlaceListViewModel by activityViewModels()
    private val locationViewModel: LocationViewModel by activityViewModels()
    private val items: MutableList<PlacesAdapter.Item> = mutableListOf()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_places, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // set views
        setViews()

        // set recyclerView adapter
        placesAdapter = PlacesAdapter(context, items)
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = placesAdapter

        // set places observer
        val placesObserver = Observer<MutableList<Place>> { places ->
            updateItems(places)
        }
        placeListViewModel.getPlaces().observe(viewLifecycleOwner, placesObserver)


        // get current location observer
        val locationObserver = Observer<Location> { location ->
            placesAdapter.updateLocation(location)
        }
        locationViewModel.getCurrentLocation().observe(viewLifecycleOwner, locationObserver)
    }

    /**
     * Set layout views
     */
    private fun setViews() {
        this.recyclerView = requireView().findViewById(R.id.places_recycler_view)
    }

    /**
     * Update places list items
     * @param items MutableList<Place>
     */
    private fun updateItems(items: MutableList<Place>) {
        // list with new items
        val newItems = mutableListOf<PlacesAdapter.Item>()
        for (item in items) {
            // new item
            val newItem = PlacesAdapter.Item(item, onClick = {
                Toast.makeText(
                    context,
                    "name: ${item.name} \n" +
                    "category: ${item.category} \n" +
                    "description: ${item.description} \n" +
                    "picture: ${item.picture} \n" +
                    "latitude: ${item.latitude} \n" +
                    "longitude: ${item.longitude}",
                    Toast.LENGTH_SHORT
                ).show()
            })
            newItems.add(newItem)
        }
        // update places adapter items list
        this.placesAdapter.updateItems(newItems)
    }
}