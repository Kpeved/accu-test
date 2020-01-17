package com.lolkek.core_network

import com.lolkek.core_network.params.WeatherDataParams
import retrofit2.Retrofit

class AccuWeatherApi(retrofit: Retrofit, private val apiKey: String) {
    private val accuService = retrofit.create(AccuService::class.java)

    fun loadWeatherData(params: WeatherDataParams) = params.run {
        accuService.getForecast(
            range = range.days,
            location = location,
            apiKey = apiKey
        )
    }

    fun loadTopCities() = accuService.getTopCities(apiKey)

    companion object {
        const val ACCU_BASE_URL = "https://dataservice.accuweather.com/"
    }
}