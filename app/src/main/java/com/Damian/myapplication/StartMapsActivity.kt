package com.Damian.myapplication

import android.Manifest
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationListener
import android.location.LocationRequest
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.content.ContextCompat
import com.Damian.myapplication.Utils.Constants.MAPS_API_KEY
import com.google.android.gms.common.ConnectionResult
import com.google.android.gms.common.api.GoogleApi
import com.google.android.gms.common.api.GoogleApiClient
import com.google.android.gms.location.LastLocationRequest
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.Marker

class StartMapsActivity : AppCompatActivity(), OnMapReadyCallback, LocationListener,
    GoogleApiClient.ConnectionCallbacks,GoogleApiClient.OnConnectionFailedListener {

    private var gMap: GoogleMap? = null
    internal lateinit var gLastLocation: Location
    internal var gCurrentLocationMarker: Marker? = null
    internal var gGoogleApiClient: GoogleApiClient? = null
    internal lateinit var gLocationRequest: LocationRequest

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start_maps)
    }

    override fun onMapReady(map: GoogleMap) {
        gMap = map

            if (ContextCompat.checkSelfPermission(this,
                    Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                buildGoogleApiClient()
                gMap!!.isMyLocationEnabled = true
            }
            else {
                Log.d("OnMapReady", "Not enabled")
                buildGoogleApiClient()
                gMap!!.isMyLocationEnabled = true
            }
    }

    private fun buildGoogleApiClient() {
        gGoogleApiClient = GoogleApiClient.Builder(this)
            .addConnectionCallbacks(this)
            .addOnConnectionFailedListener(this)
           // .addApi(MAPS_API_KEY)
            .build()
        gGoogleApiClient!!.connect()
    }

    override fun onLocationChanged(location: Location) {

        gLastLocation = location
        if (gCurrentLocationMarker != null){
            gCurrentLocationMarker!!.remove()
        }



    }

    override fun onConnected(p0: Bundle?) {
        TODO("Not yet implemented")
    }

    override fun onConnectionSuspended(p0: Int) {
        TODO("Not yet implemented")
    }

    override fun onConnectionFailed(p0: ConnectionResult) {
        TODO("Not yet implemented")
    }
}