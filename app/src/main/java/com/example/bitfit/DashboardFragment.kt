package com.example.bitfit

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView

class DashboardFragment: Fragment(), OnListFragmentInteractionListerner{
    private lateinit var healthRecyclerView: RecyclerView
    private val articles = mutableListOf<HealthData>()
    private lateinit var addButton: Button

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view=inflater.inflate(R.layout.data_dashboard,container,false)
        healthRecyclerView= view.findViewById<View>(R.id.healthList) as RecyclerView
        addButton = view.findViewById<Button>(R.id.button)
        val context=view.context
        Log.d("Debugging","View initialized")
        return view
    }

    private fun updateAdapter(recyclerView: RecyclerView) {
        val model : List<> = gson.fromJson(moviesRawJSON, arrayMovieType)
        moviesList.addAll(models)
        recyclerView.adapter = HealthAdapter(entries, this@MainActivity)
        // Look for this in Logcat:
        Log.d("MoviesFragment", "response successful")
    }



    override fun onItemClick(item: TopRated) {
        Toast.makeText(context, "Release date: " + item.date, Toast.LENGTH_SHORT).show()
    }


    override fun RequestParam(): Any {
        TODO("Not yet implemented")
    }
}


