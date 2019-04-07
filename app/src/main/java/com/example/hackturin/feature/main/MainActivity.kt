package com.example.hackturin.feature.main

import android.app.Notification
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.example.hackturin.R
import com.example.hackturin.feature.map.MapActivity
import com.example.hackturin.service.GoogleService
import com.example.hackturin.utils.CITY_WONDER_CHANNEL_ID
import com.example.hackturin.utils.EventObserver
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
        viewModel.showNotificationEvent.observe(this, EventObserver {
            with(NotificationManagerCompat.from(this)) {
                notify(NOTIFICATION_ID, buildNotification())
            }
        })
//        startActivity(Intent(this, MapActivity::class.java))

    }

    private fun buildNotification(): Notification {
        val intent = Intent(this, MapActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }
        val pendingIntent: PendingIntent = PendingIntent.getActivity(this, 0, intent, 0)

        val builder = NotificationCompat.Builder(this, CITY_WONDER_CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_notifications)
            .setContentTitle("Attention!")
            .setContentText("New attraction nearby")
            .setContentIntent(pendingIntent)
            .setStyle(NotificationCompat.BigTextStyle())
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setAutoCancel(true)
        return builder.build()
    }


    override fun onDestroy() {
        unregisterReceiver(broadcastReceiver)
        super.onDestroy()
    }

    companion object {
        const val NOTIFICATION_ID = 123
    }
}
