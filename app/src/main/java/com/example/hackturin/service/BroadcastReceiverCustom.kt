package com.example.hackturin.service

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import org.koin.core.KoinComponent

class BroadcastReceiverCustom : BroadcastReceiver(), KoinComponent {

    override fun onReceive(context: Context, intent: Intent) {
        val latitude = intent.getStringExtra("latutide").toDouble()
        val longitude = intent.getStringExtra("longitude").toDouble()

        val broadcastIntent = Intent()
        broadcastIntent.putExtra("LATITUDE", latitude)
        broadcastIntent.putExtra("LONGITUDE", longitude)
        context.sendBroadcast(broadcastIntent)
    }
}
