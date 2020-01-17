package com.lolkek.weather.repositories

import com.lolkek.core_network.data.weather.WeatherForecast

sealed class WeatherForecastResult {
    class Success(val data: WeatherForecast) : WeatherForecastResult()
    object Loading : WeatherForecastResult()
    class Error(val throwable: Throwable) : WeatherForecastResult()
}