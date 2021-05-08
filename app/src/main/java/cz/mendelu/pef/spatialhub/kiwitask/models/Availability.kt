package cz.mendelu.pef.spatialhub.kiwitask.models

import com.google.gson.annotations.Expose

data class Availability(
    @Expose val seats: Int? = 0
)