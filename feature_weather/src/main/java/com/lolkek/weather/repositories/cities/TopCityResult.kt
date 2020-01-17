package com.lolkek.weather.repositories.cities

import com.lolkek.core_network.data.city.TopCity

sealed class TopCityResult() {
    class Success(val data: List<TopCity>) : TopCityResult()
    object Loading : TopCityResult()
    class Error(val throwable: Throwable) : TopCityResult()
}