package com.lolkek.weather.features.entry

import com.lolkek.core_network.params.DaysRange
import com.lolkek.weather.features.entry.view.CityUiModel

sealed class EntryUiState {
  class ShowCities(val data: List<CityUiModel>) : EntryUiState()
  class UpdatePeriodView(val range: DaysRange) : EntryUiState()
  object OpenDetailsFragment : EntryUiState()
  class ShowError(val throwable: Throwable) : EntryUiState()
}