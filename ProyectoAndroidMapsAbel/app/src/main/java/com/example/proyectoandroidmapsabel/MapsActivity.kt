package com.example.proyectoandroidmapsabel

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.example.proyectoandroidmapsabel.databinding.ActivityMapsBinding
import com.google.android.gms.maps.model.Marker
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    /*
    PARA VER LA LATITUD Y LA LONGITUD,
    PUCHE EL MARCADOR
     */

    private lateinit var mMap: GoogleMap
    private lateinit var binding: ActivityMapsBinding
    var lats: String = ""
    var longs: String = ""
    private var numerito: Int = 0

    private val database : FirebaseDatabase = FirebaseDatabase.getInstance()
    private val referenciaDB : DatabaseReference = database.getReference("DatosProyecto")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMapsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

        binding.btnSave.setOnClickListener{
            referenciaDB.child("coordenadas").child("Marcador $numerito").setValue("Latitud: {$lats}, Longitud:{$longs}")
            numerito++
            }

        binding.btnDelete.setOnClickListener{
            referenciaDB.child("coordenadas").removeValue()
            numerito = 0
        }

    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        // Marcador de CDMX
        val marcador = LatLng(19.4284700, -99.1276600)
        mMap.addMarker(
            MarkerOptions().position(marcador)
                .title("Latitud: ${marcador.latitude} y Longitud: ${marcador.longitude}")
                .draggable(true)
        )
        mMap.moveCamera(CameraUpdateFactory.newLatLng(marcador))
        mMap.setOnMarkerDragListener(object : GoogleMap.OnMarkerDragListener {
            override fun onMarkerDragStart(marcadorNuevo: Marker) {

            }

            override fun onMarkerDragEnd(marcadorNuevo: Marker) {
                mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(marcadorNuevo.position, 1.0f))
                val mensaje = "Latittud: ${marcadorNuevo.position.latitude.toString().dropLast(2)}, Longitud: ${marcadorNuevo.position.longitude.toString().dropLast(2)}"
                lats = marcadorNuevo.position.latitude.toString()
                longs = marcadorNuevo.position.longitude.toString()
                marcadorNuevo.title = mensaje
            }

            override fun onMarkerDrag(marcadorNuevo: Marker) {

            }
        })
    }




}