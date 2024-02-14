package com.example.earthquakelist

import android.content.Context
import android.os.Bundle
import android.preference.PreferenceManager
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.earthquakelist.databinding.ActivityMapDisplayBinding
import com.example.earthquakelist.models.FeatureCollection
import com.example.earthquakelist.models.Features
import org.osmdroid.config.Configuration
import org.osmdroid.tileprovider.tilesource.TileSourceFactory
import org.osmdroid.util.GeoPoint
import org.osmdroid.views.MapView
import org.osmdroid.views.overlay.Marker


class MapDisplayActivity : AppCompatActivity() {

    private var map: MapView? = null
    private lateinit var binding: ActivityMapDisplayBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val ctx: Context = getApplicationContext()
        Configuration.getInstance().load(ctx, PreferenceManager.getDefaultSharedPreferences(ctx))


        binding = ActivityMapDisplayBinding.inflate(layoutInflater)
        setContentView(binding.root)

        map = findViewById(R.id.mapView) as MapView;
        map!!.setTileSource(TileSourceFactory.MAPNIK)
        map!!.setBuiltInZoomControls(true);
        map!!.setMultiTouchControls(true);


        val currQuake = intent.getParcelableExtra<Features>("currQuake" )

        val mapController = map!!.controller
        mapController.setZoom(7.5)
        val startPoint = GeoPoint(currQuake?.geometry!!.getY(), currQuake?.geometry!!.getX())
        mapController.setCenter(startPoint)
        val marker = Marker(map)
        marker.position = startPoint
        marker.title = currQuake.properties.place
        marker.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_CENTER)
        map!!.overlays.add(marker)
        map!!.invalidate()
    }


}