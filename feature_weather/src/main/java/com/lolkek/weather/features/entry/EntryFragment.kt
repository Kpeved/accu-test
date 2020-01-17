package com.lolkek.weather.features.entry

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.lolkek.core_network.params.DaysRange
import com.lolkek.weather.R
import com.lolkek.weather.features.details.DetailsFragment
import com.lolkek.weather.features.entry.view.CitiesRecyclerViewAdapter
import com.lolkek.weather.features.entry.view.CityUiModel
import com.test.core.Navigator
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_entry.*
import javax.inject.Inject

class EntryFragment : DaggerFragment() {

  @Inject
  lateinit var viewModelFactory: ViewModelProvider.Factory
  private val viewModel: EntryViewModel by viewModels { viewModelFactory }
  private val observer = Observer<EntryUiState> { handleResponse(it) }

  private var recyclerViewAdapter = CitiesRecyclerViewAdapter()


  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?) =
    inflater.inflate(R.layout.fragment_entry, container, false)

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    activity?.setTitle(R.string.entries_title)
    retainInstance = true
    viewModel.liveData.observe(viewLifecycleOwner, observer)

    viewModel.subscribe()
    viewModel.startLoadingTopCities()
    setUpView()
  }

  private fun setUpView() {
    rvCities.adapter = recyclerViewAdapter
    rvCities.layoutManager = LinearLayoutManager(context)
    recyclerViewAdapter.callback = object : CitiesRecyclerViewAdapter.Callback {
      override fun onClicked(id: String) {
        viewModel.startWeatherSearch(id)
      }
    }

    switchPeriodDays.setOnClickListener {
      viewModel.switchPeriod()
    }
  }

  private fun handleResponse(state: EntryUiState) {
    when (state) {
      is EntryUiState.ShowCities -> showCities(state.data)
      is EntryUiState.ShowError -> showError(state.throwable)
      is EntryUiState.UpdatePeriodView -> updatePeriodView(state.range)
      EntryUiState.OpenDetailsFragment -> openDetailsFragment()
    }
  }

  private fun updatePeriodView(range: DaysRange) {
    switchPeriodDays.text = if (range == DaysRange.ONE) {
      getString(R.string.range_one_day)
    } else {
      getString(R.string.range_five_days)
    }
  }

  private fun showCities(result: List<CityUiModel>) {
    recyclerViewAdapter.bindData(result)
    rvCities.visibility = View.VISIBLE
    error.visibility = View.GONE
  }

  private fun showError(throwable: Throwable) {
    error.visibility = View.VISIBLE
    error.setText(getString(R.string.error, throwable.message))
    rvCities.visibility = View.GONE
  }

  override fun onDestroyView() {
    viewModel.unsubscribe()
    super.onDestroyView()
  }

  private fun openDetailsFragment() {
    (activity as Navigator).addFragment(DetailsFragment.newInstance())
  }

  companion object {
    fun newInstance() = EntryFragment()
  }
}