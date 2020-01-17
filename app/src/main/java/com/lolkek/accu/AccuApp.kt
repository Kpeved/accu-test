package com.lolkek.accu

import com.lolkek.accu.di.DaggerAppComponent
import dagger.android.support.DaggerApplication
import io.reactivex.plugins.RxJavaPlugins
import timber.log.Timber

class AccuApp : DaggerApplication() {

    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
        RxJavaPlugins.setErrorHandler(Timber::w)
    }

    override fun applicationInjector() = DaggerAppComponent.builder().application(this).build()
}