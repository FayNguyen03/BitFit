package com.example.bitfit

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SharedViewModel : ViewModel() {
    private val _entries = MutableLiveData<MutableList<HealthData>>(mutableListOf())
    val entries: LiveData<MutableList<HealthData>> = _entries

    fun addEntry(entry: HealthData) {
        val currentList = _entries.value ?: mutableListOf()
        currentList.add(entry)
        _entries.value = currentList
        _entries.postValue(_entries.value)
    }
}