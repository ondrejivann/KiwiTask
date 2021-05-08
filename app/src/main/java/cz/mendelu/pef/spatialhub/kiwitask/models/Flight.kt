package cz.mendelu.pef.spatialhub.kiwitask.models

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

@Entity(tableName = "Flights")
data class Flight constructor(
    @Expose(serialize = false, deserialize = false) @PrimaryKey(autoGenerate = true) val id: Int?,
    @Expose @SerializedName("id") val apiId: String?,
    @Expose @SerializedName(value = "cityTo") val destinationCity: String?,
    @Expose val cityCodeFrom: String?,
    @Expose val cityCodeTo: String?,
    @Expose @SerializedName(value = "countryTo")val countryDestination: CountryDestination?,
    @Expose @SerializedName(value = "dTimeUTC") val dTime: Long?,
    @Expose @SerializedName(value = "aTimeUTC") val aTime: Long?,
    @Expose val airlines: List<String>? = listOf(),
    @Expose @SerializedName(value = "route") val routes: List<Route>? = listOf(),
    @Expose @SerializedName(value = "fly_duration") val flyDuration: String?,
    @Expose val price: Int?,
    @Expose val availability: Availability?
)
