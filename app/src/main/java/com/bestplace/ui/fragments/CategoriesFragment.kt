package com.bestplace.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bestplace.R
import com.bestplace.data.model.Place
import com.bestplace.data.viewModel.PlaceListViewModel
import com.bestplace.ui.adapters.CategoriesAdapter


class CategoriesFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private val placeListViewModel: PlaceListViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_categories, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // set layout views
        setViews()

        val list = arrayOf(
            CategoriesAdapter.Item(R.drawable.ic_tourist_spots, "Tourist spots", onClick = {
                // call placeListViewModel get by category
                placeListViewModel.getByCategory("Tourist spots")
                // change container fragment with places fragment
                fragmentManager?.beginTransaction()?.replace(R.id.container, PlacesFragment())
                    ?.addToBackStack(null)?.commit()
            }),
            CategoriesAdapter.Item(R.drawable.ic_food, "Food", onClick = {
                // call placeListViewModel get by category
                placeListViewModel.getByCategory("food")
                // change container fragment with places fragment
                fragmentManager?.beginTransaction()?.replace(R.id.container, PlacesFragment())
                    ?.addToBackStack(null)?.commit()
            }),
            CategoriesAdapter.Item(R.drawable.ic_hotel, "Hotel", onClick = {
                // call placeListViewModel get by category
                placeListViewModel.getByCategory("hotel")
                // change container fragment with places fragment
                fragmentManager?.beginTransaction()?.replace(R.id.container, PlacesFragment())
                    ?.addToBackStack(null)?.commit()
            }),
            CategoriesAdapter.Item(R.drawable.ic_car_wash, "Car wash", onClick = {
                // call placeListViewModel get by category
                placeListViewModel.getByCategory("car wash")
                // change container fragment with places fragment
                fragmentManager?.beginTransaction()?.replace(R.id.container, PlacesFragment())
                    ?.addToBackStack(null)?.commit()
            }),
            CategoriesAdapter.Item(R.drawable.ic_shopping, "Shopping", onClick = {
                // call placeListViewModel get by category
                placeListViewModel.getByCategory("shopping")
                // change container fragment with places fragment
                fragmentManager?.beginTransaction()?.replace(R.id.container, PlacesFragment())
                    ?.addToBackStack(null)?.commit()
            }),
            CategoriesAdapter.Item(R.drawable.ic_cinema, "Cinema", onClick = {
                // call placeListViewModel get by category
                placeListViewModel.getByCategory("cinema")
                // change container fragment with places fragment
                fragmentManager?.beginTransaction()?.replace(R.id.container, PlacesFragment())
                    ?.addToBackStack(null)?.commit()
            })
        )

        // set recyclerView adapter
        val categoriesAdapter = CategoriesAdapter(list)
        recyclerView.layoutManager = GridLayoutManager(context, 2)
        recyclerView.adapter = categoriesAdapter
    }

    private fun setViews() {
        this.recyclerView = requireView().findViewById(R.id.categories_recycler_view)
    }
}