package com.Damian.myapplication

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Address
import android.location.Geocoder
import android.location.Location
import android.location.LocationListener
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.google.android.gms.location.LocationServices
import androidx.core.content.ContextCompat
import com.Damian.myapplication.Utils.Constants.Lat
import com.Damian.myapplication.Utils.Constants.Long
import com.google.android.gms.common.ConnectionResult
import com.google.android.gms.common.api.GoogleApiClient
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions


import android.view.View
import android.widget.EditText
import android.widget.Toast
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.maps.SupportMapFragment
import java.io.IOException


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

    /*    Moved this to the main Activity, request permission on start up
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            if (ContextCompat.checkSelfPermission(
                    this,
                    Manifest.permission.ACCESS_FINE_LOCATION
                ) == PackageManager.PERMISSION_GRANTED
            ){
                buildGoogleApiClient()
                gMap!!.isMyLocationEnabled = true
            }
        }else{
            buildGoogleApiClient()
            gMap!!.isMyLocationEnabled = true
        }*/
        val mapFragment = supportFragmentManager.findFragmentById(R.id.theMap) as SupportMapFragment
        mapFragment.getMapAsync(this)
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

    override fun onBackPressed() {
        //trigger a back press activity, refresh the textfields on the main activity with the latest location information
        refreshContent()
        super.onBackPressed()
    }
    private fun refreshContent() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }


    fun buildGoogleApiClient() {
        gGoogleApiClient = GoogleApiClient.Builder(this)
            .addConnectionCallbacks(this)
            .addOnConnectionFailedListener(this)
            .addApi(LocationServices.API)
            .build()
        gGoogleApiClient!!.connect()
    }

    override fun onLocationChanged(location: Location) {

        gLastLocation = location
        if (gCurrentLocationMarker != null){
            gCurrentLocationMarker!!.remove()
        }

        val latLong = LatLng(location.latitude, location.longitude)

        val markerOptions = MarkerOptions()
        markerOptions.position(latLong)
        markerOptions.title("Your Position")
        markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN))
        gCurrentLocationMarker = gMap!!.addMarker((markerOptions))
        gMap!!.moveCamera(CameraUpdateFactory.newLatLng(latLong))
        gMap!!.moveCamera(CameraUpdateFactory.zoomTo(11f))

        if (gGoogleApiClient != null){
            LocationServices.getFusedLocationProviderClient(this)
        }
    }

    override fun onConnected(p0: Bundle?) {
        gLocationRequest = LocationRequest()
        gLocationRequest.interval = 1000
        gLocationRequest.fastestInterval = 1000
        gLocationRequest.priority = LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY
        if (ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED
        ){
            LocationServices.getFusedLocationProviderClient(this)
        }

    }

    override fun onConnectionSuspended(p0: Int) {
        TODO("Not needed for this application currently")
    }

    override fun onConnectionFailed(p0: ConnectionResult) {
        TODO("Not needed for this application currently")
    }

    fun searchLocation(view: View){
        try {
            val locationFind: EditText = findViewById(R.id.editSearch)
            var location: String = locationFind.text.toString().trim().capitalize()
            var addressList: List<Address>? = null
            if (location == null || location == "") {
                Toast.makeText(this, "location not provided", Toast.LENGTH_SHORT).show()
            } else {
                val geo = Geocoder(this)
                try {
                    addressList = geo.getFromLocationName(location, 1)
                } catch (e: IOException) {
                    e.printStackTrace()
                }
                if (addressList != null && addressList.isNotEmpty()) {
                    val address = addressList!![0]
                    val latLng = LatLng(address.latitude, address.longitude)
                    Lat = address.latitude
                    Long = address.longitude
                    Log.d("Maps", "Marker Locations Lat=$Lat & Long=$Long")
                    gMap!!.addMarker(MarkerOptions().position(latLng))
                    gMap!!.animateCamera(CameraUpdateFactory.newLatLng(latLng))
                    Toast.makeText(this, "Address found, Press Back", Toast.LENGTH_LONG).show()

                }else {
                    Toast.makeText(this, "Invalid address", Toast.LENGTH_SHORT).show()
                    Toast.makeText(this, "Try adding the type of street {avenue/street/lane}", Toast.LENGTH_LONG).show()
                    val intent = Intent(this, StartMapsActivity::class.java)
                    startActivity(intent)
                    finish()
                }
            }
        }
        catch (e: IOException){
            e.printStackTrace()
        }
    }
}