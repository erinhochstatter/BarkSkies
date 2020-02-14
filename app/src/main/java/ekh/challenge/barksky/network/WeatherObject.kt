package ekh.challenge.barksky.network

import com.squareup.moshi.Json

data class Coord(
    val lon: Double,
    val lat: Double
)

data class Weather(
    val id: Int,
    @Json(name = "main") val conditions: String,
    val description: String,
    @Json(name = "icon") val iconId: String
)

data class Readings (
    val temp: Double,
    @Json(name="feels_like") val feelsLike: Double,
    @Json(name="temp_min") val tempMin: Double,
    @Json(name="temp_max") val tempMax: Double,
    val pressure: Double,
    val humidity: Double
)

data class Wind (
    val speed: Double,
    @Json (name = "deg") val degrees: Int
)

data class Cloudiness (
    val all: String
)

data class Daylight (
    val type: Int,
    val id: Int,
    val country: String,
    val sunrise: Int, //TODO: implement datetime adapter.
    val sunset: Int //TODO: implement datetime adapter.
)

data class WeatherObject (
    val coord: Coord,
    val weather: List<Weather>,
    val base: String,
    @Json(name = "main") val currentReadings: Readings,
    val visibility: Int,
    val wind: Wind,
    val clouds: Cloudiness,
    @Json(name = "dt") val dateTimeOfCalculation: Int, //TODO: implement datetime adapter.
    @Json(name = "sys") val daylight: Daylight,
    val timezone: Int,//TODO: implement datetime adapter (i.e. -21600)
    val id: Int,
    @Json(name = "name") val cityName: String,
    val cod: Int
)