package com.example.bitfit

interface OnListFragmentInteractionListener {
    fun onItemClick(item: HealthData)

    abstract fun RequestParam(): Any
}
