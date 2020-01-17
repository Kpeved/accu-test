package com.lolkek.accu.screen.details

import com.lolkek.accu.screen.details.view.ForecastViewModel
import com.lolkek.accu.utils.DateUtils
import com.lolkek.accu.weather.ForecastRepository
import com.lolkek.accu.weather.WeatherForecastResult
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import timber.log.Timber
import javax.inject.Inject

class DetailsPresenter @Inject constructor(
    private val forecastRepository: ForecastRepository
) : DetailsContract.Presenter {
    private var view: DetailsContract.View? = null

    private var disposables = CompositeDisposable()
    override fun subscribe(view: DetailsContract.View) {
        this.view = view
        forecastRepository.forecastDataObservable
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                handleResult(it)
            }, Timber::e)
            .addTo(disposables)
    }

    private fun handleResult(result: WeatherForecastResult) {
        when (result) {
            is WeatherForecastResult.Success -> {
                view?.showWeather(result.data.dailyForecasts.map {
                    ForecastViewModel(
                        DateUtils.formatFrom_yyyy_MM_dd_To_dd_MMM(it.date),
                        it.temperature.minimum.value.toInt().toString(),
                        it.temperature.maximum.value.toInt().toString()
                    )
                })
            }
        }
    }

    override fun unsubscribe() {
        disposables.dispose()
        this.view = null
    }
}