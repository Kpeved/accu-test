package com.lolkek.accu.screen.details.di

import com.lolkek.accu.screen.details.DetailsContract
import com.lolkek.accu.screen.details.DetailsPresenter
import dagger.Binds
import dagger.Module

@Module
abstract class DetailsFragmentModule {
    @Binds
    abstract fun presenter(presenter: DetailsPresenter): DetailsContract.Presenter
}