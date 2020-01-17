package com.lolkek.weather.di

import com.lolkek.weather.features.details.DetailsFragment
import com.lolkek.weather.features.details.di.DetailsModule
import com.lolkek.weather.features.details.di.EntryModule
import com.lolkek.weather.features.entry.EntryFragment
import com.test.core.di.FragmentScope
import com.test.core.di.ViewModelModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class WeatherFragmentBindingModule {
    @FragmentScope
    @ContributesAndroidInjector(modules = [ViewModelModule::class, DetailsModule::class])
    abstract fun detailsFragment(): DetailsFragment

    @FragmentScope
    @ContributesAndroidInjector(modules = [ViewModelModule::class, EntryModule::class])
    abstract fun entryFragment(): EntryFragment

}