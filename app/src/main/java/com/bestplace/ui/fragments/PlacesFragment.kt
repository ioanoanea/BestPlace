package com.bestplace.ui.fragments

import android.annotation.SuppressLint
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
import com.bestplace.ui.adapters.PlacesAdapter


class PlacesFragment : Fragment() {

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

    @SuppressLint("NotifyDataSetChanged")
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
    }

    private fun setViews() {
        this.recyclerView = requireView().findViewById(R.id.places_recycler_view)
    }

    private fun updateItems(newItems: MutableList<Place>) {
        this.placesAdapter.clearItems()
        for (item in newItems) {
            // set item location
            setLocation(item.locationId)
            // new item
            val newItem = PlacesAdapter.Item(item, onClick = {
                Toast.makeText(
                    context,
                    "name: ${item.name} \n" +
                    "category: ${item.category} \n" +
                    "description: ${item.description} \n" +
                    "picture: ${item.picture} \n",
                    Toast.LENGTH_SHORT
                ).show()
            })
            // add new item
            this.placesAdapter.addItem(newItem)
        }
    }

    private fun setLocation(locationId: String) {
        locationViewModel.getById(locationId)
        val locationObserver = Observer<Location> { location ->
            placesAdapter.setLocation(locationId, location)
        }
        locationViewModel.getLocation().observe(viewLifecycleOwner, locationObserver)
    }
}