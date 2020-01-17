package com.lolkek.weather.repositories

import com.jakewharton.rxrelay2.BehaviorRelay
import com.lolkek.core_network.AccuWeatherApi
import com.lolkek.core_network.params.WeatherDataParams
import io.reactivex.Observable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import timber.log.Timber

class ForecastRepository(val accuWeatherApi: AccuWeatherApi) {

  private var disposable: Disposable? = null
  private var forecastDataStream = BehaviorRelay.create<WeatherForecastResult>()
  val forecastDataObservable: Observable<WeatherForecastResult> = forecastDataStream

  fun startWeatherSearch(params: WeatherDataParams) {
    forecastDataStream.accept(WeatherForecastResult.Loading)

    disposable?.dispose()
    disposable = accuWeatherApi.loadWeatherData(params)
        .map { WeatherForecastResult.Success(it) }
        .subscribeOn(Schedulers.io())
        .subscribe({ forecastDataStream.accept(it) }, {
          Timber.e(it)
          forecastDataStream.accept(WeatherForecastResult.Error(it))
        })
  }
}