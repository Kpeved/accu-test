package com.lolkek.accu.screen.entry

import com.lolkek.accu.cities.CitiesRepository
import com.lolkek.accu.cities.TopCityResult
import com.lolkek.accu.screen.entry.view.CityViewModel
import com.lolkek.accu.weather.ForecastRepository
import com.lolkek.core_network.params.DaysRange
import com.lolkek.core_network.params.DaysRange.FIVE
import com.lolkek.core_network.params.DaysRange.ONE
import com.lolkek.core_network.params.WeatherDataParams
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import timber.log.Timber
import javax.inject.Inject

class EntryPresenter
@Inject constructor(
    private val forecastRepository: ForecastRepository,
    private val citiesRepository: CitiesRepository
) : EntryContract.Presenter {
  private var view: EntryContract.View? = null
  private var range = DaysRange.ONE


  private var disposables = CompositeDisposable()
  override fun subscribe(view: EntryContract.View) {
    this.view = view
    citiesRepository.citiesDataObservable
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe({
          handleCitiesResult(it)
        }, Timber::e)
        .addTo(disposables)

    view.updatePeriodView(range)
  }

  fun handleCitiesResult(result: TopCityResult) {
    when (result) {
      is TopCityResult.Success -> view?.showCities(result.data.map {
        CityViewModel(
            it.englishName,
            it.key
        )
      })
      is TopCityResult.Error   -> view?.showError(result.throwable)
    }

  }

  override fun switchPeriod() {
    if (range == FIVE) {
      range = ONE
    } else {
      range = FIVE
    }
    view?.updatePeriodView(range)
  }

  override fun unsubscribe() {
    disposables.dispose()
    this.view = null
  }

  override fun startLoadingTopCities() {
    citiesRepository.startTopCitiesRequest()
  }

  override fun startWeatherSearch(id: String) {
    forecastRepository.startWeatherSearch(WeatherDataParams(id, range))
    view?.openDetailsFragment()
  }
}