package com.bestplace.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bestplace.R
import com.bestplace.ui.adapters.CategoriesAdapter


class CategoriesFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView

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
            CategoriesAdapter.Item(R.drawable.ic_tourist_spots, "Tourist spots"),
            CategoriesAdapter.Item(R.drawable.ic_food, "Food"),
            CategoriesAdapter.Item(R.drawable.ic_hotel, "Hotel"),
            CategoriesAdapter.Item(R.drawable.ic_car_wash, "Car wash"),
            CategoriesAdapter.Item(R.drawable.ic_shopping, "Shopping"),
            CategoriesAdapter.Item(R.drawable.ic_cinema, "Cinema")
        )

        val categoriesAdapter = CategoriesAdapter(list)
        recyclerView.layoutManager = GridLayoutManager(context, 2)
        recyclerView.adapter = categoriesAdapter
    }

    private fun setViews() {
        this.recyclerView = requireView().findViewById(R.id.categories_recycler_view)
    }
}