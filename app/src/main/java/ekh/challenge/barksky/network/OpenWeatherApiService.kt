package ekh.challenge.barksky.network

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.http.GET
import ekh.challenge.barksky.BuildConfig
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.http.Query

private var BASE_URL = "https://api.openweathermap.org/"
private var apiKey = BuildConfig.OPEN_WEATHER_MAPS_KEY

private val retrofit = Retrofit.Builder()
    .addConverterFactory(ScalarsConverterFactory.create())
    .baseUrl(BASE_URL)
    .build()


interface  OpenWeatherApiService {
    @GET("data/2.5/weather")
    fun getCurrentWeatherForPoint(@Query("lat") lat: String, @Query("lon") lon: String, @Query("appid") appId: String = apiKey): Call<String>
}

object OpenWeatherApi {
    val retrofitService : OpenWeatherApiService by lazy { retrofit.create(OpenWeatherApiService::class.java) }
}

