package ekh.challenge.barksky

import android.content.pm.PackageManager
import android.location.Location
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.navigation.findNavController
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.model.LatLng

class MainActivity : AppCompatActivity() {
    private lateinit var lastLocation: Location
    private lateinit var fusedLocationClient: FusedLocationProviderClient

    companion object {
        private const val LOCATION_PERMISSION_REQUEST_CODE = 1
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //Location services
        verifyPermissions()
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)
        getCurrentLocation()

        setContentView(R.layout.activity_main)

        // Pass args
        findNavController(R.id.nav_host_fragment).setGraph(R.navigation.navigation, intent.extras)
    }

    private fun verifyPermissions() {
        if (ActivityCompat.checkSelfPermission(this,
                android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION), LOCATION_PERMISSION_REQUEST_CODE)
            return
        }
        //The app currently has a default location of 0 0
        // TODO: Notify users that denying location services prevents the app from retrieving local weather
        // TODO better: Allow users the option of selecting a location from a fixed list of cities, if they still decline.
    }

    fun getCurrentLocation() {
        fusedLocationClient.lastLocation.addOnSuccessListener(this) { location ->
            if (location != null) {
                val currentLatLng = LatLng(location.latitude, location.longitude)

                intent.putExtra("latitude", currentLatLng.latitude.toFloat())
                intent.putExtra("longitude", currentLatLng.longitude.toFloat())
            }
        }
    }
}
