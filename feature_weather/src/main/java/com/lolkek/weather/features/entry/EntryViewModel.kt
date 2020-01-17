package com.lolkek.weather.features.entry

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.lolkek.core_network.params.DaysRange
import com.lolkek.core_network.params.DaysRange.FIVE
import com.lolkek.core_network.params.DaysRange.ONE
import com.lolkek.core_network.params.WeatherDataParams
import com.lolkek.weather.features.entry.view.CityUiModel
import com.lolkek.weather.repositories.ForecastRepository
import com.lolkek.weather.repositories.cities.CitiesRepository
import com.lolkek.weather.repositories.cities.TopCityResult
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import timber.log.Timber
import javax.inject.Inject

class EntryViewModel
@Inject constructor(
  private val forecastRepository: ForecastRepository,
  private val citiesRepository: CitiesRepository
) : ViewModel() {

  private var range = DaysRange.ONE
  val liveData = MutableLiveData<EntryUiState>()

  private var disposables = CompositeDisposable()
  fun subscribe() {
    citiesRepository.citiesDataObservable
      .observeOn(AndroidSchedulers.mainThread())
      .subscribe({
        handleCitiesResult(it)
      }, Timber::e)
      .addTo(disposables)
    liveData.value = EntryUiState.UpdatePeriodView(range)
  }

  fun handleCitiesResult(result: TopCityResult) {
    when (result) {
      is TopCityResult.Success -> liveData.value = EntryUiState.ShowCities(
        result.data.map {
          CityUiModel(
            it.englishName,
            it.key
          )
        })
      is TopCityResult.Error -> liveData.value = EntryUiState.ShowError(result.throwable)
    }
  }

  fun switchPeriod() {
    if (range == FIVE) {
      range = ONE
    } else {
      range = FIVE
    }
    liveData.value = EntryUiState.UpdatePeriodView(range)
  }

  fun startLoadingTopCities() {
    citiesRepository.startTopCitiesRequest()
  }

  fun unsubscribe(){
    disposables.dispose()
  }

  fun startWeatherSearch(id: String) {
    forecastRepository.startWeatherSearch(WeatherDataParams(id, range))
    liveData.value = EntryUiState.OpenDetailsFragment
  }
}