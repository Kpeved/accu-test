package com.lolkek.accu.screen.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.lolkek.accu.R
import com.lolkek.accu.screen.details.view.ForecastViewModel
import com.lolkek.accu.screen.details.view.ForecastsRecyclerViewAdapter
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_details.*
import javax.inject.Inject

class DetailsFragment : DetailsContract.View, DaggerFragment() {
    @Inject
    lateinit var presenter: DetailsContract.Presenter

    private var recyclerViewAdapter = ForecastsRecyclerViewAdapter()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?) =
        inflater.inflate(R.layout.fragment_details, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        activity?.setTitle(R.string.forecast_title)

        presenter.subscribe(this)
        setUpView()
    }

    private fun setUpView() {
        rvForecasts.adapter = recyclerViewAdapter
        rvForecasts.layoutManager = LinearLayoutManager(context)
    }

    override fun onDestroyView() {
        presenter.unsubscribe()
        super.onDestroyView()
    }

    override fun showWeather(result: List<ForecastViewModel>) {
        recyclerViewAdapter.bindData(result)
    }

    companion object {
        fun newInstance() = DetailsFragment()
    }
}