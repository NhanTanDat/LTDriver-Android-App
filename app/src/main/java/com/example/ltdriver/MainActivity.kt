package com.example.ltdriver

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsControllerCompat
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.mapbox.geojson.Point
import com.mapbox.maps.CameraOptions
import com.mapbox.maps.MapView
import com.mapbox.maps.Style
import com.mapbox.maps.plugin.PuckBearing
import com.mapbox.maps.plugin.locationcomponent.createDefault2DPuck
import com.mapbox.maps.plugin.locationcomponent.location
import com.mapbox.maps.plugin.viewport.viewport
import com.mapbox.maps.plugin.scalebar.scalebar
import com.mapbox.android.core.permissions.PermissionsManager
import com.mapbox.android.core.permissions.PermissionsListener

class MainActivity : AppCompatActivity(), PermissionsListener {
    companion object {
        private const val LOCATION_PERMISSION_REQUEST_CODE = 1
    }

    private lateinit var mapView: MapView
    private lateinit var permissionsManager: PermissionsManager
    private lateinit var fusedLocationClient: FusedLocationProviderClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main) // Uncomment this line if you have a layout file

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)
        mapView = findViewById(R.id.mapView)

        enableEdgeToEdge()

        if (checkLocationPermission()) {
            initializeMap()
        } else {
            requestLocationPermission()
        }

    }

    private fun initializeMap() {
        with(mapView) {
            location.locationPuck = createDefault2DPuck(withBearing = true)
            location.enabled = true
            location.puckBearing = PuckBearing.COURSE
//            viewport.transitionTo(
//                targetState = viewport.makeFollowPuckViewportState(),
//                transition = viewport.makeImmediateViewportTransition()
//            )
            WindowCompat.setDecorFitsSystemWindows(window, false)
            mapView.scalebar.enabled = false

            mapView.getMapboxMap().loadStyleUri(Style.MAPBOX_STREETS) { style ->
                fusedLocationClient.lastLocation
                    .addOnSuccessListener { location ->
                        if (location != null) {
                            mapView.mapboxMap.setCamera(
                                CameraOptions.Builder()
                                    .center(Point.fromLngLat(location.longitude, location.latitude))
                                    .pitch(0.0)
                                    .zoom(15.0)
                                    .bearing(0.0)
                                    .build()
                            )
                        }
                    }
            }
        }
    }

    private fun checkLocationPermission(): Boolean {
        return ContextCompat.checkSelfPermission(
            this,
            Manifest.permission.ACCESS_FINE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED
    }

    private fun requestLocationPermission() {
        ActivityCompat.requestPermissions(
            this,
            arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
            LOCATION_PERMISSION_REQUEST_CODE
        )
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == LOCATION_PERMISSION_REQUEST_CODE) {
            permissionsManager.onRequestPermissionsResult(requestCode, permissions, grantResults)
        }
    }

    override fun onExplanationNeeded(permissionsToExplain: List<String>) {
        // Provide an explanation to the user if necessary
    }

    override fun onPermissionResult(granted: Boolean) {
        if (granted) {
            initializeMap()
        } else {
            // Handle permission denied case
        }
    }
}
