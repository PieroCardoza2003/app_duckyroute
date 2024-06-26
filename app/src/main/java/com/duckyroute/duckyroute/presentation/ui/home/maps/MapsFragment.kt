package com.duckyroute.duckyroute.presentation.ui.home.maps

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.fragment.findNavController
import com.duckyroute.duckyroute.R
import com.duckyroute.duckyroute.databinding.FragmentMapsBinding
import com.duckyroute.duckyroute.presentation.ui.home.emergency.EmergencyDialog
import com.duckyroute.duckyroute.presentation.ui.home.vehicle.VehicleDialog
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.gms.maps.model.Polyline
import com.google.android.gms.maps.model.PolylineOptions
import com.google.maps.android.PolyUtil

class MapsFragment : Fragment(), OnMapReadyCallback {

    private lateinit var map: GoogleMap
    private lateinit var polyline: Polyline
    private var decodedPath: List<LatLng> = emptyList()
    private lateinit var marker: Marker
    private var currentIndex = 0
    private var totalPoints = 0

    private lateinit var drawerLayout: DrawerLayout
    private var _binding: FragmentMapsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMapsBinding.inflate(inflater, container, false)
        fragmentActions()
        return binding.root
    }

    private fun fragmentActions(){

        binding.bottomNavigation.setOnItemSelectedListener{ item ->
            when(item.itemId){
                R.id.button_vehiculo -> showVehicleDialog()
                R.id.button_emergencia -> showEmergencyDialog()
            }
            true
        }

        binding.buttonChatbotHome.setOnClickListener{
            findNavController().navigate(R.id.action_start_fragment_chatbot)
        }

        binding.buttonNavegarHome.setOnClickListener{
            Toast.makeText(context, "Start route", Toast.LENGTH_SHORT).show()
            drawRoute()
            simulateMovement()
        }

        binding.butonMenuHome.setOnClickListener{
            drawerLayout.openDrawer(GravityCompat.START)
        }

    }

    private fun showEmergencyDialog() {
        val emergencyDialog = EmergencyDialog()
        emergencyDialog.show(parentFragmentManager, "EmergencyDialog")
    }

    private fun showVehicleDialog() {
        val vehicleDialog = VehicleDialog()
        vehicleDialog.show(parentFragmentManager, "VehicleDialog")
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        drawerLayout = requireActivity().findViewById(R.id.drawer_layout)

        val mapFragment = childFragmentManager.findFragmentById(R.id.fragmet_map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    private fun drawRoute(){
        // Agregar los markers que son los clientes

        val markerA = LatLng(-8.111364130466, -79.02817853710928) // Replace with your coordinates
        val markerB = LatLng( -8.114577462399993, -79.02442022276831) // Replace with your coordinates
        val markerC = LatLng( -8.11643780072255, -79.01630568044219) // Replace with your coordinates
        val markerD = LatLng(-8.112316036166561, -79.00640918503527) // Replace with your coordinates
        val markerE = LatLng( -8.109036169155743, -78.996259830762) // Replace with your coordinates
        val markerF = LatLng( -8.099794947022502, -78.9867208469855) // Replace with your coordinates

        map.addMarker(MarkerOptions().position(markerA).title("A").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)) )
        map.addMarker(MarkerOptions().position(markerB).title("B"))
        map.addMarker(MarkerOptions().position(markerC).title("C").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN)))
        map.addMarker(MarkerOptions().position(markerD).title("D"))
        map.addMarker(MarkerOptions().position(markerE).title("E") )
        map.addMarker(MarkerOptions().position(markerF).title("F").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_YELLOW)) )

        marker = map.addMarker(MarkerOptions().position(markerA).title("X"))!!


        // Dibujar ruta en base a un string de rutas
        val polylineStr = "~fop@`ejaNiBhBbEbDfD_DROjDiD`DwCdDaDfBcBLKeCyBgAcAu@o@}DeDjAkApCsC`BcBFGoAoAnEyCfDiBOoGRuDHw@Pw@^mBBUsA_Ag@]y@k@}@o@uBuAeAs@MKoAq@[O{GoCa@QL_@h@wAh@sAPg@Jk@B_@Aa@YeDYqDKyA_@kEEi@}@aKAOKgAAIUBA?@?TCQwB]}D]yDUsCGq@CSEiADq@Lo@Tq@Vc@b@o@f@w@Ti@Fa@@OIeEYUeNgLe@_@gEmDHO|@{AKIKT}@pAkDuCaDkCePyMiLoJcCaCqAeBeA}AkA}Bw@eBg@yAk@{B"

        decodedPath = PolyUtil.decode(polylineStr)

        totalPoints = decodedPath.size

        // Dibujar la polilínea inicial en el mapa
        polyline = map.addPolyline(PolylineOptions()
            .addAll(decodedPath)
            .color(Color.BLUE)
            .width(12f))

        /*decodedPath = PolyUtil.decode(polylineStr)

        val polylineOptions = PolylineOptions().addAll(decodedPath).color(Color.BLACK).width(10f)
        polyline = map.addPolyline(polylineOptions)

         */

        if (decodedPath.isNotEmpty()) {
            val firstPoint = decodedPath.first()
            map.moveCamera(CameraUpdateFactory.newLatLngZoom(firstPoint, 12f))
        }

    }

    private fun simulateMovement() {
        val handler = android.os.Handler()
        val delay: Long = 1000 // Intervalo de actualización en ms

        handler.postDelayed(object : Runnable {
            override fun run() {
                if (currentIndex < totalPoints - 1) {
                    currentIndex++
                    moveMarker()
                    handler.postDelayed(this, delay)
                }
            }
        }, delay)
    }

    private fun moveMarker() {
        marker.position = decodedPath[currentIndex]
        //map.animateCamera(CameraUpdateFactory.newLatLngZoom(marker.position, 15f))
        val cameraPosition = CameraPosition.Builder()
            .target(marker.position) // Ubicación del conductor
            .zoom(17f) // Nivel de zoom deseado
            .bearing(0f) // Rumbo de la cámara (0 grados, mirando hacia el este)
            .tilt(45f) // Ángulo de inclinación de la cámara (45 grados)
            .build()

        map.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition))

        updatePolyline()
    }

    private fun updatePolyline() {
        //val points = decodedPath.subList(0, currentIndex + 1)

        val pointsToShow = decodedPath.subList(currentIndex, totalPoints)
        //val pointsToHide = decodedPath.subList(0, currentIndex)

        polyline.points = pointsToShow
        //polyline.points = points

        // marcar la ruta ya recorrida
        //map.addPolyline(PolylineOptions()
        //    .addAll(pointsToHide)
        //    .color(Color.RED)
        //    .width(12f))
    }


    override fun onMapReady(map: GoogleMap) {
        this.map = map
        this.map.uiSettings.isCompassEnabled = false
        //this.map.uiSettings.isMyLocationButtonEnabled = false
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}