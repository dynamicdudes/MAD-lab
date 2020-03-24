package io.chillout.gpslocation

import android.Manifest
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.material.snackbar.BaseTransientBottomBar.LENGTH_INDEFINITE
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    //Declaring TAG and REQ. CODE
    private val TAG = "MainActivity"
    private val REQUEST_CODE = 59
    //entry point for fusedLocation API
    private lateinit var fusedLocationClient :FusedLocationProviderClient
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //getting permission
        /*
        * @param fusedLocation acts as LocationService from FusedLocation
        * */
        if (ContextCompat.checkSelfPermission(this,Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION),REQUEST_CODE)
        }
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)
        fetch_btn.setOnClickListener {
            if(ContextCompat.checkSelfPermission(this,Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED){
                getLastLocation()
            }
            Log.d(TAG,"Button Clicked but permission Granted??")
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when(requestCode){
            REQUEST_CODE ->{
                if(grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    Toast.makeText(this,"Permission Granted!",Toast.LENGTH_SHORT).show()
                }else{
                    Toast.makeText(applicationContext,"GO to Settings and Grant Permissions",Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun getLastLocation() {
        fusedLocationClient.lastLocation
            .addOnCompleteListener {
                if(it.isSuccessful && it.result != null){
                    val location = it.result!!
                    latitude_text.text = "Latitude : ${location.latitude}"
                    longitude_text.text = "Longitude : ${location.longitude}"
                }else{
                    showSnackbar("NO location Detected !")
                }
            }
    }
    // Shows a [Snackbar]
    private fun showSnackbar(resourceString: String, actionStringId:Int = 0,listner: View.OnClickListener?=null) {
        val snackbar = Snackbar.make(
            findViewById(android.R.id.content),
            resourceString,
            LENGTH_INDEFINITE)
        if(actionStringId != 0 && listner != null){
            snackbar.setAction(resourceString,listner)
        }
        snackbar.show()
    }

}
