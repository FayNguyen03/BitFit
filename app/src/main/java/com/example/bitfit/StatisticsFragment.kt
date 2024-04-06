package com.example.bitfit

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.w3c.dom.Text


class StatisticsFragment: Fragment(), OnListFragmentInteractionListener{
    private lateinit var addButton: Button
    private lateinit var avgHour: TextView
    private lateinit var avgMood: TextView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view=inflater.inflate(R.layout.fragment_statistics,container,false)
        avgHour=view.findViewById(R.id.avgHour) as TextView
        avgMood=view.findViewById(R.id.avgMood) as TextView
        addButton = view.findViewById(R.id.buttonAdd) as Button
        val context=view.context
        return view
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        addButton.setOnClickListener {
            val fragmentManager = parentFragmentManager
            fragmentManager.beginTransaction()
                .replace(R.id.dashboard, InputFragment())
                .addToBackStack(null)
                .commit()
        }

        lifecycleScope.launch {
            val hourAvg = withContext(Dispatchers.IO) {
                (requireActivity().application as HealthApplication).db.healthDao().hour_avg().toString()
            }
            val moodAvg = withContext(Dispatchers.IO) {
                (requireActivity().application as HealthApplication).db.healthDao().mood_avg()
            }

            avgHour.text = hourAvg
            if (moodAvg.toDouble() < 2) {
                avgMood.text = "😒😒😒"
            }
            else if (moodAvg.toDouble() < 3.5){
                avgMood.text ="🙄🙄🙄"
            }
            else{
                avgMood.text ="🫡🫡🫡"
            }
        }
    }


    override fun onItemClick(item: HealthData) {

    }


    override fun RequestParam(): Any {
        TODO("Not yet implemented")
    }
}