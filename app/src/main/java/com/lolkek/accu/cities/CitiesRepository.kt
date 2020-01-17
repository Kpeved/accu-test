package com.lolkek.accu.cities

import com.jakewharton.rxrelay2.BehaviorRelay
import com.lolkek.core_network.AccuWeatherApi
import io.reactivex.Observable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import timber.log.Timber

class CitiesRepository(private val accuWeatherApi: AccuWeatherApi) {

  private var disposable: Disposable? = null
  private val citiesDataStream = BehaviorRelay.create<TopCityResult>()
  val citiesDataObservable: Observable<TopCityResult> = citiesDataStream

  fun startTopCitiesRequest() {
    disposable?.dispose()
    citiesDataStream.accept(TopCityResult.Loading)
    disposable = accuWeatherApi.loadTopCities()
        .map { TopCityResult.Success(it) }
        .subscribeOn(Schedulers.io())
        .subscribe({ citiesDataStream.accept(it) }, {
          Timber.e(it)
          citiesDataStream.accept(TopCityResult.Error(it))
        })
  }
}