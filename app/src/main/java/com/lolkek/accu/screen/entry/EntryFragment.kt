package com.lolkek.accu.screen.entry

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.lolkek.accu.R
import com.lolkek.accu.screen.details.DetailsFragment
import com.lolkek.accu.screen.entry.view.CitiesRecyclerViewAdapter
import com.lolkek.accu.screen.entry.view.CityViewModel
import com.lolkek.core_network.params.DaysRange
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_entry.*
import javax.inject.Inject

class EntryFragment : EntryContract.View, DaggerFragment() {
  @Inject
  lateinit var presenter: EntryContract.Presenter

  private var recyclerViewAdapter = CitiesRecyclerViewAdapter()

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?) =
      inflater.inflate(R.layout.fragment_entry, container, false)


  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    activity?.setTitle(R.string.entries_title)
    retainInstance = true
    presenter.subscribe(this)
    presenter.startLoadingTopCities()
    setUpView()
  }

  private fun setUpView() {
    rvCities.adapter = recyclerViewAdapter
    rvCities.layoutManager = LinearLayoutManager(context)
    recyclerViewAdapter.callback = object : CitiesRecyclerViewAdapter.Callback {
      override fun onClicked(id: String) {
        presenter.startWeatherSearch(id)
      }
    }

    switchPeriodDays.setOnClickListener {
      presenter.switchPeriod()
    }
  }

  override fun updatePeriodView(range: DaysRange) {
    switchPeriodDays.text = if(range == DaysRange.ONE ){
      getString(R.string.range_one_day)
    } else {
      getString(R.string.range_five_days)
    }
  }

  override fun onDestroyView() {
    presenter.unsubscribe()
    super.onDestroyView()
  }

  override fun showCities(result: List<CityViewModel>) {
    recyclerViewAdapter.bindData(result)
    rvCities.visibility = View.VISIBLE
    error.visibility = View.GONE
  }

  override fun showError(throwable: Throwable) {
    error.visibility = View.VISIBLE
    error.setText(getString(R.string.error, throwable.message))
    rvCities.visibility = View.GONE
    "dfd".length
  }

  override fun openDetailsFragment() {
    activity?.apply {
      supportFragmentManager
          .beginTransaction()
          .add(R.id.container, DetailsFragment.newInstance())
          .addToBackStack(null)
          .commit()
    }
  }

  companion object {
    fun newInstance() = EntryFragment()
  }
}