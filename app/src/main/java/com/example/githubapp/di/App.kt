package com.example.githubapp.di

import android.app.Application
import com.example.githubapp.event_bus.EventBus
import com.example.githubapp.event_bus.analytic.AnalyticObserver
import com.example.githubapp.event_bus.analytic.FirebaseAnalyticsApi
import com.example.githubapp.event_bus.analytic.UserClickEvent
import com.github.terrakok.cicerone.Cicerone

class App : Application() {
    private val cicerone = Cicerone.create()
    val router get() = cicerone.router
    val navigatorHolder get() = cicerone.getNavigatorHolder()

    val repoContainer = RepoContainer()

    val userClicksEventBus by lazy {
        val eventBus = EventBus<UserClickEvent>()
        AnalyticObserver(eventBus, FirebaseAnalyticsApi())
        eventBus
    }
}