package com.lolkek.accu.weather

import com.lolkek.core_network.data.weather.WeatherForecast

sealed class WeatherForecastResult {
    class Success(val data: WeatherForecast) : WeatherForecastResult()
    object Loading : WeatherForecastResult()
    class Error(val throwable: Throwable) : WeatherForecastResult()
}