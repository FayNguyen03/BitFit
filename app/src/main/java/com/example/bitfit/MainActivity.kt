package com.example.bitfit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val supportFragmentManager = supportFragmentManager
        supportFragmentManager.beginTransaction()
            .replace(R.id.dashboard, DashboardFragment())
            .commit()



    }
}