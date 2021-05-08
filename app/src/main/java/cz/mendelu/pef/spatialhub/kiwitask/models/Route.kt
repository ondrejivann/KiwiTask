package cz.mendelu.pef.spatialhub.kiwitask.models

import com.google.gson.annotations.Expose

data class Route(
    @Expose val id: String?,
    @Expose val cityCodeFrom: String?,
    @Expose val cityCodeTo: String?
)