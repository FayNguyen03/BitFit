package com.example.bitfit

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SharedViewModel : ViewModel() {
    private val _entries = MutableLiveData<MutableList<HealthData>>(mutableListOf())
    val entries: LiveData<MutableList<HealthData>> = _entries

    fun addEntry(entry: HealthData) {
        val currentList = _entries.value ?: mutableListOf()

        currentList.add(entry)
        Log.d("viewModel",currentList.size.toString())
            _entries.value = currentList
        _entries.postValue(_entries.value)

    }
    fun getEntry(): MutableList<HealthData>{
        return _entries.value ?: mutableListOf()
    }

}