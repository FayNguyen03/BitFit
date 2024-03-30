package com.example.bitfit

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.flow.forEach
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.Date


class DashboardFragment: Fragment(), OnListFragmentInteractionListener{
    private lateinit var healthRecyclerView: RecyclerView
    var entries = mutableListOf<HealthData>(HealthData("12/03/2021",6.0,7.0,"hehe"))
    private lateinit var addButton: Button
    private lateinit var viewModel: SharedViewModel
    private lateinit var inputView: View
    private lateinit var adapterHealth: HealthAdapter
    private lateinit var application: HealthApplication

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view=inflater.inflate(R.layout.data_dashboard,container,false)
        healthRecyclerView= view.findViewById<View>(R.id.healthList) as RecyclerView
        addButton = view.findViewById<Button>(R.id.button)
        val context=view.context
        val layoutManager = LinearLayoutManager(context)
        healthRecyclerView.setLayoutManager(layoutManager)
        viewModel = ViewModelProvider(requireActivity()).get(SharedViewModel::class.java)
        application=requireActivity().application as HealthApplication
        adapterHealth = HealthAdapter(context,entries)
        updateAdapter(entries)
        lifecycleScope.launch {
            (requireActivity().application as HealthApplication).db.healthDao().getAll()
                .collect { entry ->
                    entry.map { entity ->
                        HealthData(
                            entity.date,
                            entity.sleepingHour,
                            entity.mood,
                            entity.note
                        )
                    }.also { mappedList ->
                        entries.clear()
                        entries.addAll(mappedList)
                        updateAdapter(entries)                   }
                }

        }
        healthRecyclerView.adapter = adapterHealth
        return view
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        addButton.setOnClickListener {
            val fragmentManager=parentFragmentManager
            fragmentManager.beginTransaction()
                .replace(R.id.dashboard,InputFragment())
                .addToBackStack(null)
                .commit()
        }

        viewModel.entries.observe(viewLifecycleOwner, Observer { entries ->
            updateAdapter(entries)

        })

    }

    private fun updateAdapter(entries: MutableList<HealthData>) {
        (healthRecyclerView.adapter as? HealthAdapter)?.let {
            it.entries = entries
            it.notifyDataSetChanged()
        }
    }



    override fun onItemClick(item: HealthData) {

    }


    override fun RequestParam(): Any {
        TODO("Not yet implemented")
    }
}