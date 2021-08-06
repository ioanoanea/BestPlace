package com.bestplace.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.RecyclerView
import com.bestplace.R
import com.bestplace.data.viewModel.PlaceListViewModel
import com.bestplace.ui.adapters.PlacesAdapter


class PlacesFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private val placeListViewModel: PlaceListViewModel by activityViewModels()
    private lateinit var placesAdapter: PlacesAdapter

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
        // placesAdapter = PlacesAdapter(placeListViewModel.places.value)
        // recyclerView.layoutManager = LinearLayoutManager(context)
        // recyclerView.adapter = placesAdapter

        // set tasks when viewModel's items are updated
    }

    private fun setViews() {
        this.recyclerView = requireView().findViewById(R.id.places_recycler_view)
    }
}