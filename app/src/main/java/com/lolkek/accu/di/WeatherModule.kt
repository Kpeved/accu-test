package com.lolkek.accu.di

import com.lolkek.accu.cities.CitiesRepository
import com.lolkek.accu.weather.ForecastRepository
import com.lolkek.core_network.AccuWeatherApi
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