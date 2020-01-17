package com.lolkek.weather.features.details

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.lolkek.weather.features.details.view.ForecastUiModel
import com.lolkek.weather.repositories.ForecastRepository
import com.lolkek.weather.repositories.WeatherForecastResult
import com.test.core.utils.DateUtils
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import timber.log.Timber
import javax.inject.Inject

class DetailsViewModel @Inject constructor(
    private val forecastRepository: ForecastRepository
) : ViewModel(){

    val liveData = MutableLiveData<DetailsUiState>()
    private var disposables = CompositeDisposable()

    fun getForecast() {
        forecastRepository.forecastDataObservable
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                handleResult(it)
            }, Timber::e)
            .addTo(disposables)
    }

    fun unsubscribe(){
        disposables.dispose()
    }

    private fun handleResult(result: WeatherForecastResult) {
        when (result) {
            is WeatherForecastResult.Success -> {
                val res = result.data.dailyForecasts.map {
                    ForecastUiModel(
                        DateUtils.formatFrom_yyyy_MM_dd_To_dd_MMM(it.date),
                        it.temperature.minimum.value.toInt().toString(),
                        it.temperature.maximum.value.toInt().toString()
                    )
                }
                liveData.value = DetailsUiState.Success(res)
            }
        }
    }
}