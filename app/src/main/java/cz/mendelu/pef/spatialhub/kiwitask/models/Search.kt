package cz.mendelu.pef.spatialhub.kiwitask.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Search(
        @Expose val currency: String?,
        @Expose @SerializedName(value = "search_id") val id: String?,
        @Expose @SerializedName(value = "data") val flights: List<Flight> = mutableListOf()
)