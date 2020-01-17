package com.lolkek.weather.features.details

import com.lolkek.weather.features.details.view.ForecastUiModel

sealed class DetailsUiState {
  class Success(val data: List<ForecastUiModel>) : DetailsUiState()
  object Loading : DetailsUiState()
  class Error(val throwable: Throwable) : DetailsUiState()
}