package cz.mendelu.pef.spatialhub.kiwitask.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

@Entity(tableName = "Flights")
data class Flight(
    @Expose(serialize = false, deserialize = false) @PrimaryKey(autoGenerate = true) val id: Int?,
    @Expose val cityFrom: String?,
    @Expose val cityTo: String?,
    @Expose val dTime: Long?,
    @Expose val aTime: Long?,
    @Expose @SerializedName(value = "fly_duration") val flyDuration: String?,
    @Expose val price: Int?
)
