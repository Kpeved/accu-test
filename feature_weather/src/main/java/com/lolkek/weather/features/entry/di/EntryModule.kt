package com.lolkek.weather.features.details.di

import androidx.lifecycle.ViewModel
import com.lolkek.weather.features.entry.EntryViewModel
import com.test.core.di.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class EntryModule {

  @Binds
  @IntoMap
  @ViewModelKey(EntryViewModel::class)
  abstract fun bindEntryViewModel(viewModel: EntryViewModel): ViewModel
}