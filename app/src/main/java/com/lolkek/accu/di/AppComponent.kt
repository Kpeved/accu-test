package com.lolkek.accu.di

import android.app.Application
import com.lolkek.core_network.di.NetworkModule
import com.lolkek.weather.di.WeatherFragmentBindingModule
import com.lolkek.weather.di.WeatherModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import dagger.android.support.DaggerApplication
import javax.inject.Singleton


@Singleton
@Component(
  modules = [
    AndroidSupportInjectionModule::class,
    AppModule::class,
    WeatherModule::class,
    NetworkModule::class,
    WeatherFragmentBindingModule::class]
)
interface AppComponent : AndroidInjector<DaggerApplication> {
  override fun inject(instance: DaggerApplication)

  @Component.Builder
  interface Builder {
    @BindsInstance
    fun application(application: Application): AppComponent.Builder

    fun build(): AppComponent
  }
}