package com.example.ltdriver
import android.Manifest
import android.content.ContentValues.TAG
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.gms.tasks.OnSuccessListener
import com.mapbox.android.core.permissions.PermissionsListener
import com.mapbox.android.core.permissions.PermissionsManager
import com.mapbox.geojson.Point
import com.mapbox.maps.CameraOptions
import com.mapbox.maps.ImageHolder
import com.mapbox.maps.MapView
import com.mapbox.maps.Style
import com.mapbox.maps.extension.style.expressions.generated.Expression.Companion.interpolate
import com.mapbox.maps.plugin.LocationPuck2D
import com.mapbox.maps.plugin.PuckBearing
import com.mapbox.maps.plugin.locationcomponent.OnIndicatorPositionChangedListener
import com.mapbox.maps.plugin.locationcomponent.location
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import java.util.concurrent.Executors
import android.util.Log
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsControllerCompat
import com.mapbox.maps.plugin.scalebar.scalebar

class MainActivity : AppCompatActivity(), PermissionsListener {

    private lateinit var mapView: MapView


    lateinit var permissionsManager: PermissionsManager

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        permissionsManager.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }
    fun checkLocationPermission(): Boolean {
        return ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED
    }

    fun requestLocationPermission() {
        ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), LOCATION_PERMISSION_REQUEST_CODE)
    }
    private lateinit var fusedLocationClient: FusedLocationProviderClient
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)
//
//        if (checkLocationPermission()) {
//
//        } else {
//            requestLocationPermission()
//        }
//
//        enableEdgeToEdge()
//        if (PermissionsManager.areLocationPermissionsGranted(this)) { // If inside an Activity
//            // if (PermissionsManager.areLocationPermissionsGranted(getActivity())) { // If inside a Fragment
//            // Permission sensitive logic called here, such as activating the Maps SDK's LocationComponent to show the device's location
//        } else {
//            permissionsManager = PermissionsManager(this)
//            permissionsManager.requestLocationPermissions(this)
//        }
        setContentView(R.layout.activity_main)

//        mapView = findViewById(R.id.mapView)
//
//        WindowCompat.setDecorFitsSystemWindows(window, false)
//        mapView.scalebar.enabled = false
//
//        mapView.location.puckBearing = PuckBearing.HEADING
//        mapView.location.puckBearing = PuckBearing.COURSE
//        mapView.getMapboxMap().loadStyleUri(Style.MAPBOX_STREETS) { style ->
//
//            mapView.location.updateSettings {
//                enabled = true
//                locationPuck = LocationPuck2D(
//                    topImage = null,
//                    bearingImage = null,
//                    shadowImage = null
//                )
//                pulsingEnabled = true
//            }
//            fusedLocationClient.lastLocation
//                .addOnSuccessListener { location->
//                    if (location != null) {
//                        mapView.mapboxMap.setCamera(
//                            CameraOptions.Builder()
//                                .center(Point.fromLngLat(location.longitude, location.latitude))
//                                .pitch(0.0)
//                                .zoom(15.0)
//                                .bearing(0.0)
//                                .build()
//                        )
//                    }else{
//
//                    }
//
//                }
//        }
    }

    override fun onExplanationNeeded(permissionsToExplain: List<String>) {
        // Provide an explanation to the user if necessary
    }

    override fun onPermissionResult(granted: Boolean) {
        if (granted) {
            // The user granted the permissions, you can proceed with your logic here
        } else {
            // The user didn't grant the permissions, handle this case here
        }
    }
    companion object {
        private const val LOCATION_PERMISSION_REQUEST_CODE = 1
    }
}