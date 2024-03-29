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
import androidx.recyclerview.widget.RecyclerView
import java.util.Date


class DashboardFragment: Fragment(), OnListFragmentInteractionListener{
    private lateinit var healthRecyclerView: RecyclerView
    var entries = mutableListOf<HealthData>(HealthData("hehe",5.6,7.5, Date(2023,12,12)))
    private lateinit var addButton: Button
    private lateinit var viewModel: SharedViewModel
    private lateinit var inputView: View
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view=inflater.inflate(R.layout.data_dashboard,container,false)
        healthRecyclerView= view.findViewById<View>(R.id.healthList) as RecyclerView
        addButton = view.findViewById<Button>(R.id.button)
        val context=view.context
        Log.d("Debugging","View initialized")
        healthRecyclerView.adapter = HealthAdapter(view.context,entries)
        return view
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(requireActivity()).get(SharedViewModel::class.java)


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


