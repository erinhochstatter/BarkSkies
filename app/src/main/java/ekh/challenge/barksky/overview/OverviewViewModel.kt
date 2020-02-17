package ekh.challenge.barksky.overview

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ekh.challenge.barksky.R
import ekh.challenge.barksky.network.OpenWeatherApi
import ekh.challenge.barksky.network.WeatherObject
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.math.roundToInt

class OverviewViewModel : ViewModel() {
    //Live Data
    private var _status = MutableLiveData<String>()
    val status: LiveData<String>
        get() = _status

    private var _currentWeather = MutableLiveData<WeatherObject>()
    val currentWeather: LiveData<WeatherObject>
        get() = _currentWeather

    private var _presentation = MutableLiveData<OverviewPresentation>()
    val presentation: LiveData<OverviewPresentation>
        get() = _presentation

    // Async Handling
    private var viewModelJob = Job()
    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main )

    init {
    }

    fun getWeatherForCurrentLocation(lat: Float, lng: Float) {

        coroutineScope.launch {
            var getWeatherDeferred = OpenWeatherApi.retrofitService.getCurrentWeatherForPoint("41.89", "-87.64")

            try {
                var weatherResult = getWeatherDeferred.await()
                _currentWeather.value = weatherResult
                setPresentationDetails(weatherResult.currentReadings.temp.roundToInt(), weatherResult.weather.get(0).conditions)
            } catch(e: Exception) {
                _status.value =  "Failure: ${e.message}"
            }
        }
    }

    private fun setPresentationDetails(temperature: Int, conditions: String) {

        when {
            conditions == "extreme" || conditions == "rain" || conditions =="wind"->
                _presentation.value = OverviewPresentation(R.drawable.stormy, "Head for the blanket fort, pups!")
            temperature < 30 ->
                _presentation.value = OverviewPresentation(R.drawable.below_0, "Oh no. Time for boots.")
            temperature in 30..75 && conditions == "sunny" ->
                _presentation.value = OverviewPresentation(R.drawable.above_80, "Time for an extended stroll!")
            temperature > 75 ->
            _presentation.value = OverviewPresentation(R.drawable.above_90, "Keep cool in the shade.")
            else ->
                _presentation.value = OverviewPresentation(R.drawable.windy, "Maybe it's time for a snooze.")
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}
