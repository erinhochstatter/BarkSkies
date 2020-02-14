package ekh.challenge.barksky.overview

import androidx.lifecycle.ViewModel
import ekh.challenge.barksky.network.OpenWeatherApi
import ekh.challenge.barksky.network.WeatherObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class OverviewViewModel : ViewModel() {

    init {
        getWeatherForCurrentLocation()
    }

    private fun getWeatherForCurrentLocation() {
        OpenWeatherApi.retrofitService.getCurrentWeatherForPoint("41.89", "-87.64").enqueue( object: Callback<WeatherObject> {
            override fun onFailure(call: Call<WeatherObject>, t: Throwable) {
                var weatherResponse = t.message.toString()
            }

            override fun onResponse(call: Call<WeatherObject>, response: Response<WeatherObject>) {
                var weatherResponse = response.body()
            }
        })
    }
}
