package com.example.bitfit

interface OnListFragmentInteractionListerner {
    fun onItemClick(item: HealthData)

    abstract fun RequestParam(): Any
}
