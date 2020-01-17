package com.lolkek.accu.screen.entry.di

import com.lolkek.accu.screen.entry.EntryContract
import com.lolkek.accu.screen.entry.EntryPresenter
import dagger.Binds
import dagger.Module

@Module
abstract class EntryFragmentModule {
  @Binds
  abstract fun presenter(presenter: EntryPresenter): EntryContract.Presenter
}