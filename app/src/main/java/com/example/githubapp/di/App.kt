package com.example.githubapp.di

import android.app.Application
import com.example.githubapp.domain.EventBus
import com.example.githubapp.domain.analytic.AnalyticObserver
import com.example.githubapp.domain.analytic.AnalyticsApiImpl
import com.example.githubapp.domain.analytic.UserClickEvent
import com.github.terrakok.cicerone.Cicerone

class App : Application() {
    private val cicerone = Cicerone.create()
    val router get() = cicerone.router
    val navigatorHolder get() = cicerone.getNavigatorHolder()

    val repoContainer = RepoContainer()

    val userClicksEventBus by lazy {
        val eventBus = EventBus<UserClickEvent>()
        AnalyticObserver(eventBus, AnalyticsApiImpl())
        eventBus
    }
}