package com.lolkek.accu.screen.details

import com.lolkek.accu.screen.details.view.ForecastViewModel

interface DetailsContract {
    interface Presenter {
        fun subscribe(view: View)
        fun unsubscribe()
    }

    interface View {
        fun showWeather(result: List<ForecastViewModel>)
    }
}