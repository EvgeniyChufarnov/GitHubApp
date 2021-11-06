package com.example.githubapp.di

import android.app.Application
import com.example.githubapp.event_bus.analytic.AnalyticObserver
import com.example.githubapp.event_bus.analytic.FirebaseAnalyticsApi
import org.koin.android.ext.android.get
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.core.qualifier.named

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@App)

            modules(
                repoModule,
                ciceroneModule,
                databaseModule,
                networkModule,
                utilsModule,
                presenterModule
            )
        }

        initAnalyticObserver()
    }

    private fun initAnalyticObserver() {
        AnalyticObserver(
            get(named(EVENT_BUS_USER)),
            get(named(EVENT_BUS_REPO)),
            FirebaseAnalyticsApi()
        )
    }
}