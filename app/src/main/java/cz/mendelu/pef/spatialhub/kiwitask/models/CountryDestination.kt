package cz.mendelu.pef.spatialhub.kiwitask.models

import com.google.gson.annotations.Expose

data class CountryDestination(
    @Expose val code: String?,
    @Expose val name: String?
)