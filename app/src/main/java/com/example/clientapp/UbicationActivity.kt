package com.example.clientapp

import android.Manifest
import android.content.pm.PackageManager
import android.location.Address
import android.location.Geocoder
import android.location.Location
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import java.util.*

class UbicationActivity : AppCompatActivity() , OnMapReadyCallback {
    // Variables para la interaccón con el mapa
    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private lateinit var lastLocation: Location
    private lateinit var btn_getLocation: Button
    private lateinit var et_setLocation: EditText
    private lateinit var map: GoogleMap
    private lateinit var geocoder: Geocoder
    private lateinit var direccion: List<Address>

    companion object{
        const val REQUEST_CODE_LOCATION = 0
    }

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ubication)

        btn_getLocation = findViewById<Button>(R.id.btn_getLocation)
        et_setLocation = findViewById<EditText>(R.id.direccion)



        createFragment()
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)
    }

    private fun createFragment() {
        //crear y referenciar un fragment de tipo SupportMapFragment
        val mapFragment: SupportMapFragment = supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    override fun onMapReady(googleMap: GoogleMap) {
        map = googleMap
        map.mapType = GoogleMap.MAP_TYPE_HYBRID
        btn_getLocation.setOnClickListener{
            map.clear()
            setUpMap()
        }
    }

    //Evaluar si el usuario ha permitido el acceso de gps
    private fun isLocationPermissionGranted() = ContextCompat.checkSelfPermission(
        this, Manifest.permission.ACCESS_FINE_LOCATION
    ) == PackageManager.PERMISSION_GRANTED


    private fun placeMarket(location: LatLng, direccion_string: String){
        val markerOptions = MarkerOptions().position(location)
        markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE))
        markerOptions.title(direccion_string)
        map.addMarker(markerOptions)
    }

    private fun setUpMap(){

        if(ActivityCompat.checkSelfPermission(
                this,
                android.Manifest.permission.ACCESS_FINE_LOCATION
            ) !=
            PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(
                this,
                arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION), REQUEST_CODE_LOCATION
            )
            return
        }
        map.isMyLocationEnabled = true
        fusedLocationClient.lastLocation.addOnSuccessListener(this){ location ->
            if(location!=null){
                lastLocation = location
                val currentLatLng = LatLng(location.latitude, location.longitude)

                map.animateCamera(CameraUpdateFactory.newLatLngZoom(currentLatLng, 18f))
                geocoder =  Geocoder(this, Locale.getDefault());
                direccion = geocoder.getFromLocation(location.latitude, location.longitude,1)
                var direccion_string = direccion.get(0).getAddressLine(0)
                placeMarket(currentLatLng,direccion_string)
                et_setLocation.setText(direccion_string)
            }
        }
    }

    override fun onResumeFragments() {
        super.onResumeFragments()
        if(!::map.isInitialized) return
        if(!isLocationPermissionGranted()){
            map.isMyLocationEnabled = false
            Toast.makeText(
                this,
                "Para activar la localizacion ve a ajustes y acepta el permiso de gps,",
                Toast.LENGTH_LONG
            ).show()
        }
    }
}