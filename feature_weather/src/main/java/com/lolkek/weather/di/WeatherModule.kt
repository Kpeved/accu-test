package com.lolkek.weather.di

import com.lolkek.core_network.AccuWeatherApi
import com.lolkek.weather.repositories.ForecastRepository
import com.lolkek.weather.repositories.cities.CitiesRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class WeatherModule {
    @Provides
    @Singleton
    fun providesWeatherRepository(accuApi: AccuWeatherApi) = ForecastRepository(accuApi)

    @Provides
    @Singleton
    fun providesCitiesRepository(accuApi: AccuWeatherApi) = CitiesRepository(accuApi)

}