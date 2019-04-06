package com.example.hackturin

import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.hackturin.feature.map.MapActivity
import com.example.hackturin.service.BroadcastReceiverCustom
import com.example.hackturin.service.GoogleService

class MainActivity : AppCompatActivity() {

    private val broadcastReceiver: BroadcastReceiverCustom by lazy { BroadcastReceiverCustom() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        registerReceiver(broadcastReceiver, IntentFilter(GoogleService.str_receiver))
        setContentView(R.layout.activity_main)
        startActivity(Intent(this, MapActivity::class.java))

    }

    override fun onDestroy() {
        unregisterReceiver(broadcastReceiver)
        super.onDestroy()
    }
}
