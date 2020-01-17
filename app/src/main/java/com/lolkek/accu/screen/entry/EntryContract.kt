package com.lolkek.accu.screen.entry

import com.lolkek.accu.screen.entry.view.CityViewModel
import com.lolkek.core_network.params.DaysRange

interface EntryContract {
  interface Presenter {
    fun subscribe(view: View)
    fun unsubscribe()
    fun startWeatherSearch(id: String)
    fun startLoadingTopCities()
    fun switchPeriod()
  }

  interface View {
    fun openDetailsFragment()
    fun updatePeriodView(range: DaysRange)
    fun showCities(result: List<CityViewModel>)
    fun showError(throwable: Throwable)
  }
}