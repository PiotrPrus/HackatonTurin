package com.example.hackturin

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.hackturin.feature.settings.SettingsFragment
import android.content.Context.BIND_AUTO_CREATE
import android.content.Intent



class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Create new fragment and transaction
        val settingsFragment = SettingsFragment()
        val transaction = supportFragmentManager.beginTransaction()

        // Replace whatever is in the fragment_container view with this fragment,
        // and add the transaction to the back stack if needed
        transaction.replace(R.id.nav_host_fragment, settingsFragment)
        transaction.addToBackStack(null)

        // Commit the transaction
        transaction.commit()

    }

}
