package cz.mendelu.pef.spatialhub.kiwitask.models

import com.google.gson.annotations.SerializedName

data class Search(
        val currency: String?,
        @SerializedName(value = "search_id") val id: String?,
        @SerializedName(value = "data") val flights: List<Flight> = mutableListOf()
)