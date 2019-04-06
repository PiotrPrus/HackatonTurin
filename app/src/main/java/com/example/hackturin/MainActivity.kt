package com.example.hackturin

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.hackturin.feature.map.MapActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//        stopService(Intent(baseContext, com.here.android.mpa.service.MapService::class.java))
        startActivity(Intent(this, MapActivity::class.java))
        finish()
    }

}
