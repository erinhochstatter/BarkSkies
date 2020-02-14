package ekh.challenge.barksky.overview

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ekh.challenge.barksky.network.OpenWeatherApi
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import java.lang.Exception

class OverviewViewModel : ViewModel() {
    private var _response = MutableLiveData<String>()
    val response: LiveData<String>
        get() = _response

    private var viewModelJob = Job()
    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main )

    init {
        getWeatherForCurrentLocation()
    }

    private fun getWeatherForCurrentLocation() {

        coroutineScope.launch {
            var getWeatherDeferred = OpenWeatherApi.retrofitService.getCurrentWeatherForPoint("41.89", "-87.64")

            try {
                var thing = getWeatherDeferred.await()
                _response.value = "Hey there, conditions are ${thing.weather.get(0).conditions}"
            } catch(e: Exception) {
                _response.value =  "Failure: ${e.message}"
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}
