package cz.mendelu.pef.spatialhub.kiwitask.models

import com.google.gson.annotations.SerializedName

data class Flight(
    val cityFrom: String?,
    val cityTo: String?,
    val dTime: Long?,
    val aTime: Long?,
    @SerializedName(value = "fly_duration") val flyDuration: String?,
    val price: Int?
)
