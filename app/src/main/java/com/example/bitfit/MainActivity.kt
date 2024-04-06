package com.example.bitfit

import android.Manifest
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.util.AttributeSet
import android.view.MenuItem
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.google.android.material.navigation.NavigationBarView


class MainActivity : AppCompatActivity() {
    companion object {
        private const val NOTIFICATION_ID = 1001
    }
    private lateinit var button: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val fragmentManager: FragmentManager = supportFragmentManager
        val fragment1: Fragment = DashboardFragment()
        val fragment2: Fragment = StatisticsFragment()
        val bottomNavigationView = findViewById<NavigationBarView>(R.id.bottom_navigation)
        button= findViewById<Button>(R.id.noti_trigger)
        bottomNavigationView.setOnItemSelectedListener { item ->
            lateinit var fragment: Fragment
            when (item.itemId) {
                R.id.add_entry -> fragment = fragment1
                R.id.statistics_conclusion -> fragment = fragment2
            }
            fragmentManager.beginTransaction().replace(R.id.dashboard, fragment).commit()
            true
        }
        bottomNavigationView.selectedItemId = R.id.add_entry
        val intent = Intent(this, InputFragment::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }
        createNotificationChannel()
        button.setOnClickListener{
            triggerNotification()
        }


    }


    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = getString(R.string.channel_name)
            val descriptionText = getString(R.string.channel_description)
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel("01", name, importance).apply {
                description = descriptionText
            }
            val notificationManager: NotificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }

    private fun triggerNotification() {
        val pendingIntent: PendingIntent =
            PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_IMMUTABLE)
        var builder = NotificationCompat.Builder(this, "01")
            .setSmallIcon(R.drawable.logo)
            .setContentTitle("Have you logged in your entry today?")
            .setContentText("How many hours do you sleep today?...")
            .setStyle(NotificationCompat.BigTextStyle()
                .bigText("How many hours do you sleep today? How do you feel? Log it into BitFit so we can keep track your lifestyle for you."))
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setContentIntent(pendingIntent)
            .setAutoCancel(true)

        with(NotificationManagerCompat.from(this)) {
            if (ActivityCompat.checkSelfPermission(
                    this@MainActivity,
                    Manifest.permission.POST_NOTIFICATIONS
                ) != PackageManager.PERMISSION_GRANTED
            ) {


                return@with
            }
            // notificationId is a unique int for each notification that you must define.
            notify(NOTIFICATION_ID, builder.build())
        }
    }
}