package ekh.challenge.barksky.overview

import androidx.lifecycle.ViewModel
import ekh.challenge.barksky.network.OpenWeatherApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class OverviewViewModel : ViewModel() {
    var weatherResponse: String = ""
//    private var apiKey = BuildConfig.OPEN_WEATHER_MAPS_KEY

    init {
        getWeatherForCurrentLocation()
    }

    private fun getWeatherForCurrentLocation() {
        OpenWeatherApi.retrofitService.getCurrentWeatherForPoint("41.89", "-87.64").enqueue( object: Callback<String> {
            override fun onFailure(call: Call<String>, t: Throwable) {
                weatherResponse = t.message.toString()
            }

            override fun onResponse(call: Call<String>, response: Response<String>) {
                weatherResponse = response.body() ?: "null body"
            }
        })
    }
}
