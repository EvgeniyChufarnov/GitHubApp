package com.example.githubapp.di

import com.example.githubapp.event_bus.EventBus
import com.example.githubapp.event_bus.analytic.AnalyticObserver
import com.example.githubapp.event_bus.analytic.FirebaseAnalyticsApi
import com.example.githubapp.event_bus.analytic.RepoClickEvent
import com.example.githubapp.event_bus.analytic.UserClickEvent
import com.example.githubapp.utils.ConnectionState
import org.koin.core.qualifier.Qualifier
import org.koin.core.qualifier.named
import org.koin.dsl.module

const val EVENT_BUS_USER = "user"
const val EVENT_BUS_REPO = "repo"

val utilsModule = module {
    single { ConnectionState(get()) }

    single(named(EVENT_BUS_USER)) { EventBus<UserClickEvent>() }

    single(named(EVENT_BUS_REPO)) { EventBus<RepoClickEvent>() }
}