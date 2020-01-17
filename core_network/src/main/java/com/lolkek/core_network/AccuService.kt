package com.lolkek.core_network

import com.lolkek.core_network.data.city.TopCity
import com.lolkek.core_network.data.weather.WeatherForecast
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface AccuService {

    @GET("forecasts/v1/daily/{range}day/{location}")
    fun getForecast(
        @Path("range") range: Int,
        @Path("location") location: String,
        @Query("apikey") apiKey: String,
        @Query("metric") metric: Boolean = true
    ): Single<WeatherForecast>

    @GET("locations/v1/topcities/50")
    fun getTopCities(@Query("apikey") apiKey: String): Single<List<TopCity>>
}