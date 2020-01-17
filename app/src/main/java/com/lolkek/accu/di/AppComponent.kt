package com.lolkek.accu.di

import android.app.Application
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import dagger.android.support.DaggerApplication
import javax.inject.Singleton


@Singleton
@Component(
    modules = [AndroidSupportInjectionModule::class,
      AppModule::class,
      WeatherModule::class,
      NetworkModule::class,
      FragmentBindingModule::class])
interface AppComponent : AndroidInjector<DaggerApplication> {
  override fun inject(instance: DaggerApplication)

  @Component.Builder
  interface Builder {
    @BindsInstance
    fun application(application: Application): AppComponent.Builder

    fun build(): AppComponent
  }
}