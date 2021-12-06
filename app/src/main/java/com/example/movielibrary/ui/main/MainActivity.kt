package com.example.movielibrary.ui.main

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.location.Geocoder
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.HandlerThread
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat
import com.example.movielibrary.R
import com.example.movielibrary.model.Film
import com.example.movielibrary.ui.detailsFragment.DetailsFragmentFilm
import com.example.movielibrary.ui.fragments.FragmentOne
import com.example.movielibrary.ui.settingInfo.FragmentSetting
import com.example.movielibrary.ui.fragments.FragmentTwo
import com.example.movielibrary.ui.map.MapsFragment
import com.example.movielibrary.ui.settingInfo.ContactsFragment
import com.example.movielibrary.ui.settingInfo.FragmentHistory
import com.example.movielibrary.ui.settingInfo.FragmentInfo
import com.google.android.material.bottomnavigation.BottomNavigationView
import java.io.IOException

class MainActivity : AppCompatActivity() {
    private lateinit var toolbar: androidx.appcompat.widget.Toolbar

    @RequiresApi(Build.VERSION_CODES.S)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        toolbar = findViewById(R.id.toolbar)

        setFirstFragment()
        navigationSelected()
        toolbarMenu()
    }

    private fun setFirstFragment(){
        val fragmentOne = FragmentOne.newInstance()
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.containerMain, fragmentOne)
            .commit()

        fragmentOne.setMainActivity(this)
    }

    fun addFragmentDetails(infoFilm: Film){
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.containerMain, DetailsFragmentFilm.newInstance(infoFilm))
            .addToBackStack("Stack1")
            .commit()
    }

    private fun navigationSelected(){
        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottomNavigationView)
        bottomNavigationView.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.options1 -> {
                    val fragmentOne = FragmentOne.newInstance()
                    supportFragmentManager
                        .beginTransaction()
                        .replace(R.id.containerMain, fragmentOne)
                        .commit()

                    fragmentOne.setMainActivity(this)
                    true
                }
                R.id.options2 -> {
                    val fragmentTwo = FragmentTwo.newInstance()
                    supportFragmentManager
                        .beginTransaction()
                        .replace(R.id.containerMain, fragmentTwo)
                        .commit()

                    fragmentTwo.setMainActivity(this)
                    true
                }
                else -> false
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.S)
    private fun toolbarMenu(){
        toolbar.setOnMenuItemClickListener {
            if(it.itemId == R.id.optionsToolbarSetting){
                supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.containerMain, FragmentSetting())
                    .addToBackStack("Stack1")
                    .commit()
            }
            if(it.itemId == R.id.optionsToolbarInfo){
                supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.containerMain, FragmentInfo())
                    .addToBackStack("Stack1")
                    .commit()
            }
            if(it.itemId == R.id.optionsToolbarFavourites){
                supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.containerMain, FragmentHistory() )
                    .addToBackStack("Stack1")
                    .commit()
            }
            if(it.itemId == R.id.contacts){
                supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.containerMain, ContactsFragment.newInstance() )
                    .addToBackStack("Stack1")
                    .commit()
            }
            if(it.itemId == R.id.optionsLocations){
                checkPermissions()

                supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.containerMain, MapsFragment() )
                    .addToBackStack("Stack1")
                    .commit()
            }
            return@setOnMenuItemClickListener false
        }
    }

    @RequiresApi(Build.VERSION_CODES.S)
    private fun checkPermissions(){
        val context = this
        context.let { notNullContext ->
            when (PackageManager.PERMISSION_GRANTED) {
                ContextCompat.checkSelfPermission(notNullContext, Manifest.permission.ACCESS_FINE_LOCATION) -> {
                    getLocation()
                }
                else -> {
                    permissionResult.launch(Manifest.permission.ACCESS_FINE_LOCATION)
                }
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.S)
    private val permissionResult = registerForActivityResult(ActivityResultContracts.RequestPermission()) { result ->
        if (result) {
            getLocation()
        } else {
            Toast.makeText(this, getString(R.string.need_permissions_to_read_contacts), Toast.LENGTH_SHORT).show()
        }
    }

    private val onLocationsListener = object : LocationListener {

        override fun onLocationChanged(location: Location) {
            getAddressAsync(this@MainActivity, location)
        }

        override fun onStatusChanged(provider: String, status: Int, extras: Bundle) {}
        override fun onProviderEnabled(provider: String) {}
        override fun onProviderDisabled(provider: String) {}
    }

    @SuppressLint("MissingPermission")
    private fun getLocation() {
        this.let { context ->

            val locationManager = context.getSystemService(Context.LOCATION_SERVICE) as LocationManager

            if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {


                val provider = locationManager.getProvider(LocationManager.GPS_PROVIDER)
                provider?.let {

                    locationManager.requestLocationUpdates(
                        LocationManager.GPS_PROVIDER,
                        1000,
                        10f,
                        onLocationsListener
                    )
                }
            } else {
                val location =
                    locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER)
                if (location == null) {
                    showDialog(
                        getString(R.string.dialog_title_gps_turned_off),
                        getString(R.string.dialog_message_last_location_unknown)
                    )
                } else {
                    getAddressAsync(context, location)
                    showDialog(
                        getString(R.string.dialog_title_gps_turned_off),
                        getString(R.string.dialog_message_last_known_location)
                    )
                }
            }

        }
    }

    private fun getAddressAsync(context: Context, location: Location) {
        val handler : Handler = Handler()

        val geoCoder = Geocoder(context)
        Thread {
            try {
                val addresses = geoCoder.getFromLocation(
                    location.latitude,
                    location.longitude,
                    1
                )

                handler.post{
                    showAddressDialog(addresses[0].getAddressLine(0), location)
                }

            } catch (e: IOException) {
                e.printStackTrace()
            }
        }.start()
    }

    private fun showAddressDialog(address: String, location: Location) {
        this.let {
            AlertDialog.Builder(it)
                .setTitle(getString(R.string.dialog_address_title))
                .setMessage(address)
                .setPositiveButton(getString(R.string.dialog_address_get_weather)) { _, _ ->

                    val text = address + " " + location.latitude + " " + location.longitude
                    Toast.makeText(this,text, Toast.LENGTH_SHORT).show()

                }
                .setNegativeButton(getString(R.string.dialog_button_close)) { dialog, _ -> dialog.dismiss() }
                .create()
                .show()
        }
    }

    private fun showDialog(title: String, message: String) {
        this.let {
            AlertDialog.Builder(it)
                .setTitle(title)
                .setMessage(message)
                .setNegativeButton(getString(R.string.dialog_button_close)) { dialog, _ -> dialog.dismiss() }
                .create()
                .show()
        }
    }
}