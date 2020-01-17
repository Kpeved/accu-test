package com.lolkek.weather.features.details.di

import androidx.lifecycle.ViewModel
import com.lolkek.weather.features.details.DetailsViewModel
import com.test.core.di.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class DetailsModule {

  @Binds
  @IntoMap
  @ViewModelKey(DetailsViewModel::class)
  abstract fun bindDetailsViewModel(viewModel: DetailsViewModel): ViewModel
}