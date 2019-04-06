package com.example.hackturin.feature.map

import android.os.Bundle
import android.os.Environment
import android.util.Log
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import com.example.hackturin.R
import com.here.android.mpa.common.GeoCoordinate
import com.here.android.mpa.common.OnEngineInitListener

import kotlinx.android.synthetic.main.activity_map.*
import com.here.android.mpa.mapping.SupportMapFragment
import com.here.android.mpa.mapping.Map
import java.io.File
import android.content.pm.PackageManager
import com.here.android.mpa.common.MapSettings


class MapActivity : AppCompatActivity() {

    // map embedded in the map fragment
    private lateinit var map: Map
    // map fragment embedded in this activity
    private lateinit var mapFragment: SupportMapFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initView()

        setSupportActionBar(toolbar)
        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }
    }

    private fun initView() {
        setContentView(R.layout.activity_map)

        mapFragment = supportFragmentManager.findFragmentById(R.id.mapFragment) as SupportMapFragment

        val diskCacheRoot = Environment.getExternalStorageDirectory().path + File.separator + "isolated-here-maps"
        var intentName = ""

        try {
            val ai = this.packageManager
                .getApplicationInfo(this.packageName, PackageManager.GET_META_DATA)
            val bundle = ai.metaData
            intentName = bundle.getString("INTENT_NAME") ?: ""
        } catch (e: PackageManager.NameNotFoundException) {
            Log.e(this.javaClass.toString(), "Failed to find intent name, NameNotFound: " + e.message)
        }

        val success = MapSettings.setIsolatedDiskCacheRootPath(diskCacheRoot, intentName)

        if (!success){
            Toast.makeText(this, "Unable to isolate disk cache", Toast.LENGTH_SHORT).show()
        } else {
            mapFragment.init { error ->
                if (error == OnEngineInitListener.Error.NONE) {
                    map = mapFragment.map
                    map.setCenter(GeoCoordinate(49.196261, -123.004773, 0.0), Map.Animation.NONE)
                    map.zoomLevel = (map.maxZoomLevel + map.minZoomLevel) / 2
                } else {
                    Log.d("AAAA", "Error occured: $error")
                }
            }
        }
    }


}
