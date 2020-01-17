package com.lolkek.weather.features.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.lolkek.weather.R
import com.lolkek.weather.features.details.view.ForecastUiModel
import com.lolkek.weather.features.details.view.ForecastsRecyclerViewAdapter
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_details.*
import javax.inject.Inject

class DetailsFragment : DaggerFragment() {

  @Inject
  lateinit var viewModelFactory: ViewModelProvider.Factory
  private val viewModel: DetailsViewModel by viewModels { viewModelFactory }
  private val observer = Observer<DetailsUiState> { handleResponse(it) }

  private var recyclerViewAdapter = ForecastsRecyclerViewAdapter()


  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?) =
    inflater.inflate(R.layout.fragment_details, container, false)

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    activity?.setTitle(R.string.forecast_title)
    viewModel.liveData.observe(viewLifecycleOwner, observer)
    viewModel.getForecast()
    setUpView()
  }

  private fun setUpView() {
    rvForecasts.adapter = recyclerViewAdapter
    rvForecasts.layoutManager = LinearLayoutManager(context)
  }

  private fun handleResponse(result: DetailsUiState) {
    when (result) {
      is DetailsUiState.Success -> showWeather(result.data)
    }
  }

  override fun onDestroyView() {
    viewModel.unsubscribe()
    super.onDestroyView()
  }

  private fun showWeather(result: List<ForecastUiModel>) {
    recyclerViewAdapter.bindData(result)
  }

  companion object {
    fun newInstance() = DetailsFragment()
  }
}