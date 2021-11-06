package com.example.githubapp.di

import android.app.Application
import com.example.githubapp.event_bus.EventBus
import com.example.githubapp.event_bus.analytic.AnalyticObserver
import com.example.githubapp.event_bus.analytic.FirebaseAnalyticsApi
import com.example.githubapp.event_bus.analytic.RepoClickEvent
import com.example.githubapp.event_bus.analytic.UserClickEvent
import com.github.terrakok.cicerone.Cicerone

class App : Application() {
    private val cicerone = Cicerone.create()
    val router get() = cicerone.router
    val navigatorHolder get() = cicerone.getNavigatorHolder()

    val repoContainer by lazy { RepoContainer(this) }

    val userClickedEventBus by lazy { EventBus<UserClickEvent>() }
    val repoClickedEventBus by lazy {  EventBus<RepoClickEvent>() }

    override fun onCreate() {
        super.onCreate()

        AnalyticObserver(
            userClickedEventBus,
            repoClickedEventBus,
            FirebaseAnalyticsApi()
        )
    }
}