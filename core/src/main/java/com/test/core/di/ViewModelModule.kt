package com.test.core.di

import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module

@Module
abstract class ViewModelModule {
  @Binds
  abstract fun viewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory
}