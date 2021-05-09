package com.myapplication.bootcamp.carapp.ui.home

import android.Manifest
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.location.Location
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.gms.tasks.Task
import com.myapplication.bootcamp.carapp.R
import com.myapplication.bootcamp.carapp.di.component.FragmentComponent
import com.myapplication.bootcamp.carapp.ui.base.BaseFragment
import com.myapplication.bootcamp.carapp.utils.common.PermissionUtils.PermissionDeniedDialog.Companion.newInstance
import com.myapplication.bootcamp.carapp.utils.common.PermissionUtils.isPermissionGranted
import com.myapplication.bootcamp.carapp.utils.common.PermissionUtils.requestPermission
import com.myapplication.bootcamp.carapp.utils.display.CustomMarker
import kotlinx.android.synthetic.main.fragment_home.*


class HomeFragment : BaseFragment<HomeViewModel>(),OnMapReadyCallback {

    private var permissionDenied = false
    private lateinit var map: GoogleMap
    private var currentLocation: Location? = null
    private lateinit var fusedLocationProviderClient: FusedLocationProviderClient

    companion object {

        const val TAG = "HomeFragment"

        /**
         * Request code for location permission request.
         */
        private const val LOCATION_PERMISSION_REQUEST_CODE = 1

        fun newInstance(): HomeFragment {
            val args = Bundle()
            val fragment = HomeFragment()
            fragment.arguments = args
            return fragment
        }
    }

    override fun provideLayoutId(): Int = R.layout.fragment_home

    override fun injectDependencies(fragmentComponent: FragmentComponent) {
        fragmentComponent.inject(this)
    }

    override fun setupObservers() {
        super.setupObservers()

        /*
         *Loading all the vehicle markers based on the lat and long values
         */
        viewModel.vehicleList.observe(this, Observer {
            it.data?.run {
                for(singleVehicle in this){
                    val latLng = LatLng(singleVehicle.lat, singleVehicle.lng)
                    val markerOptions = MarkerOptions().position(latLng).title("License Plate Number: "+singleVehicle.license_plate_number).icon(
                        CustomMarker.BitmapFromVector(context!!,R.drawable.ic_car)
                    )
                    map.addMarker(markerOptions)
                }
                val latLng = LatLng(this.last().lat, this.last().lng)
                map.animateCamera(CameraUpdateFactory.newLatLng(latLng))
                map.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 5f))
            }
        })

        /*
         * displaying the progress dialog
         */
        viewModel.loggingIn.observe(this, Observer {
            pb_home_loading.visibility = if (it) View.VISIBLE else View.GONE
        })
    }

    @SuppressLint("MissingPermission")
    override fun setupView(view: View) {

        /*
         * on click of the locate me button the user will be located on the map
         */
        bt_userLocation.setOnClickListener {
            //do nothing
            val task: Task<Location> = fusedLocationProviderClient.getLastLocation()
            task.addOnSuccessListener {
                currentLocation = it
                map.clear()
                showUserLocation()
            }
        }
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view=inflater.inflate(R.layout.fragment_home,container,false)

        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(activity as AppCompatActivity);

        if (ContextCompat.checkSelfPermission(this.context!!, Manifest.permission.ACCESS_FINE_LOCATION)
            == PackageManager.PERMISSION_GRANTED) {

            val task: Task<Location> = fusedLocationProviderClient.getLastLocation()
            task.addOnSuccessListener {
                it?.let {
                    currentLocation = it
                }
                val supportMapFragment = childFragmentManager.findFragmentById(R.id.mapView) as? SupportMapFragment
                supportMapFragment?.getMapAsync(this)
            }

        }else{
            requestPermission(
                activity as AppCompatActivity, LOCATION_PERMISSION_REQUEST_CODE,
                Manifest.permission.ACCESS_FINE_LOCATION, true
            )
        }
        return view
    }

    @SuppressLint("MissingPermission")
    override fun onMapReady(googleMap: GoogleMap?) {
        map = googleMap ?: return
        viewModel.onMapReady()
        map.setOnMapClickListener {
            map.clear()
        }
        showUserLocation()
        enableMyLocation()
    }



    private fun enableMyLocation() {
        if (!::map.isInitialized) return
        if (ContextCompat.checkSelfPermission(this.context!!, Manifest.permission.ACCESS_FINE_LOCATION)
            == PackageManager.PERMISSION_GRANTED) {
            map.isMyLocationEnabled = true
        } else {
            // Permission to access the location is missing. Show rationale and request permission
            requestPermission(
                activity as AppCompatActivity, LOCATION_PERMISSION_REQUEST_CODE,
                Manifest.permission.ACCESS_FINE_LOCATION, true
            )
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        if (requestCode != LOCATION_PERMISSION_REQUEST_CODE) {
            return
        }
        if (isPermissionGranted(permissions, grantResults, Manifest.permission.ACCESS_FINE_LOCATION)) {
            // Enable the my location layer if the permission has been granted.
            enableMyLocation()
        } else {
            // Permission was denied. Display an error message
            // Display the missing permission error dialog when the fragments resume.
            permissionDenied = true
        }
    }


    @SuppressLint("MissingPermission")
    override fun onResume() {
        super.onResume()

        if (permissionDenied) {
            // Permission was not granted, display error dialog.
            showMissingPermissionError()
            permissionDenied = false
        }else{
            val task: Task<Location> = fusedLocationProviderClient.getLastLocation()
            task.addOnSuccessListener {
                it?.let {
                    currentLocation = it
                }
                val supportMapFragment = childFragmentManager.findFragmentById(R.id.mapView) as? SupportMapFragment
                supportMapFragment?.getMapAsync(this)
            }
        }
    }

    /**
     * Displays a dialog with error message explaining that the location permission is missing.
     */
    private fun showMissingPermissionError() {
        newInstance(true).show(childFragmentManager, "dialog")
    }


    /*
     * show current location of the user on the maps
     */
    private fun showUserLocation() {
        currentLocation?.let {
            val latLng = LatLng(it.getLatitude(), it.getLongitude())
            val markerOptions = MarkerOptions().position(latLng).title("I am here!")
            map.animateCamera(CameraUpdateFactory.newLatLng(latLng))
            map.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 5f))
            map.addMarker(markerOptions)
        }
    }
}