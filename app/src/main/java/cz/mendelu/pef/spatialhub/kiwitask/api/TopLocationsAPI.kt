package cz.mendelu.pef.spatialhub.kiwitask.api

import cz.mendelu.pef.spatialhub.kiwitask.models.Search
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface TopLocationsAPI {
    @GET("flights")
    suspend fun getTopLocations(@Query("v") v: Int, @Query("sort") sort: String, @Query("asc") asc: Int, @Query("locale") locale: String, @Query("flyFrom") flyFrom: String, @Query("to") to: String, @Query("date_from") dateFrom: String, @Query("date_to") dateTo: String, @Query("typeFlight") typeFlight: String, @Query("oneforcity") oneForCity: Int, @Query("adults") adults: Int, @Query("limit") limit: Int, @Query("partner") partner: String): Response<Search>
}