package com.example.hackturin.feature.main

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.hackturin.R
import com.example.hackturin.service.GoogleService
import org.koin.android.ext.android.inject

class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by inject()

    private val broadcastReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent) {
            val latitude = intent.getStringExtra("latutide").toDouble()
            val longitude = intent.getStringExtra("longitude").toDouble()
            viewModel.getGeoDataByLocation(latitude, longitude)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        registerReceiver(broadcastReceiver, IntentFilter(GoogleService.str_receiver))
        setContentView(R.layout.activity_main)
        startService(Intent(this, GoogleService::class.java))
//        startActivity(Intent(this, MapActivity::class.java))

    }

    override fun onDestroy() {
        unregisterReceiver(broadcastReceiver)
        super.onDestroy()
    }
}
