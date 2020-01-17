package com.lolkek.accu.di

import com.lolkek.accu.screen.details.DetailsFragment
import com.lolkek.accu.screen.details.di.DetailsFragmentModule
import com.lolkek.accu.screen.entry.EntryFragment
import com.lolkek.accu.screen.entry.di.EntryFragmentModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentBindingModule {
    @FragmentScope
    @ContributesAndroidInjector(modules = [DetailsFragmentModule::class])
    abstract fun detailsFragment(): DetailsFragment

    @FragmentScope
    @ContributesAndroidInjector(modules = [EntryFragmentModule::class])
    abstract fun entryFragment(): EntryFragment

}