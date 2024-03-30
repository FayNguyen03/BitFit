package com.example.bitfit

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.core.app.ActivityOptionsCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import java.io.Serializable
import java.text.ParseException
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.util.Date
import java.util.Locale

class InputFragment: Fragment(){
    private lateinit var dateInput: EditText
    private lateinit var moodInput: EditText
    private lateinit var hourInput: EditText
    private lateinit var noteInput: EditText
    private lateinit var addButton: Button
    private lateinit var viewModel: SharedViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view=inflater.inflate(R.layout.data_input,container,false)
        dateInput= view.findViewById<EditText>(R.id.dateInput1)
        moodInput= view.findViewById<EditText>(R.id.moodInput)
        noteInput= view.findViewById<EditText>(R.id.noteInput)
        hourInput=view.findViewById<EditText>(R.id.timeInput)
        addButton = view.findViewById<Button>(R.id.entryButton)
        val context=view.context
        viewModel= ViewModelProvider(requireActivity()).get(SharedViewModel::class.java)
        Log.d("Debugging","Input View initialized")
        setupTextInputListeners()
        return view
    }
    private fun setupTextInputListeners() {
        val textWatcher = object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                addButton.isEnabled = dateInput.text.isNotEmpty() && moodInput.text.isNotEmpty() && noteInput.text.isNotEmpty() && hourInput.text.isNotEmpty()
            }

            override fun afterTextChanged(s: Editable?) {}
        }

        dateInput.addTextChangedListener(textWatcher)
        moodInput.addTextChangedListener(textWatcher)
        noteInput.addTextChangedListener(textWatcher)
        hourInput.addTextChangedListener(textWatcher)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        addButton.setOnClickListener {

            if (dateInput.text != null && noteInput.text!=null && moodInput.text!=null && hourInput.text != null) {
                val entry = HealthData(
                    noteInput.text.toString(),
                    moodInput.text.toString().toDouble(),
                    hourInput.text.toString().toDouble(),
                    dateInput.text.toString()
                )
                viewModel.addEntry(entry)
                parentFragmentManager.popBackStack()
            } else {
                // Handle date parsing error, perhaps with a Toast
                Toast.makeText(
                    context,
                    "Invalid input!",
                    Toast.LENGTH_LONG
                ).show()
            }
        }
    }
}