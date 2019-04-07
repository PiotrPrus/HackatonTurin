package com.example.hackturin.feature.map

import android.content.pm.PackageManager
import android.os.Bundle
import android.os.Environment
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.example.hackturin.R
import com.example.hackturin.data.model.GeoItem
import com.example.hackturin.utils.KEY_GEO_ITEM
import com.here.android.mpa.common.GeoCoordinate
import com.here.android.mpa.common.MapSettings
import com.here.android.mpa.common.OnEngineInitListener
import com.here.android.mpa.mapping.Map
import com.here.android.mpa.mapping.MapMarker
import com.here.android.mpa.mapping.SupportMapFragment
import kotlinx.android.synthetic.main.activity_map.*
import org.koin.android.ext.android.inject
import java.io.File


class MapActivity : AppCompatActivity() {

    // map embedded in the map fragment
    private lateinit var map: Map
    // map fragment embedded in this activity
    private lateinit var mapFragment: SupportMapFragment
    private val viewModel: MapViewModel by inject()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initView()
        setSupportActionBar(toolbar)
        val geoItem = intent.getParcelableExtra<GeoItem>(KEY_GEO_ITEM)
        addItemToMap(geoItem)
//        viewModel.loadGeoFences(45.07, 7.68, 400)
//        viewModel.mapMakers.observe(this, Observer { list -> addMarkersToMap(list) })
    }

    private fun addItemToMap(geoItem: GeoItem?) {
        geoItem?.let {
            val marker = MapMarker(GeoCoordinate(geoItem.nearestLat, geoItem.nearestLon))
            map.addMapObject(marker)
        }
    }

    private fun addMarkersToMap(list: List<MapMarker>?) {
        Log.d("aaaa", "List of markers: $list")
        if (this::map.isInitialized) map.addMapObjects(list)
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

        if (!success) {
            Toast.makeText(this, "Unable to isolate disk cache", Toast.LENGTH_SHORT).show()
        } else {
            mapFragment.init { error ->
                if (error == OnEngineInitListener.Error.NONE) {
                    map = mapFragment.map
                    map.setCenter(GeoCoordinate(45.07, 7.68, 0.0), Map.Animation.NONE)
                    map.zoomLevel = (map.maxZoomLevel) / 1.2
                } else {
                    Log.d("AAAA", "Error occured: $error")
                }
            }
        }
    }


}
