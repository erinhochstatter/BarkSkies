package ekh.challenge.barksky.network

import com.squareup.moshi.Json

data class StationObject(
    val id: String,
    @Json(name = "external_id") val externalID: String,
    val name: String,
    val longitude: Double,
    val latitude: Double,
    val altitude: Int,
    val rank: Int
)

data class StationList(
    @field:Json(name = "count") val count: Int?,
    @field:Json(name = "stations") val releases: List<StationObject>)