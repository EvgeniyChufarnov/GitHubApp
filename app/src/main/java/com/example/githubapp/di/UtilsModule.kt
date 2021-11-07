package com.example.githubapp.di

import android.content.Context
import com.example.githubapp.event_bus.EventBus
import com.example.githubapp.event_bus.analytic.RepoClickEvent
import com.example.githubapp.event_bus.analytic.UserClickEvent
import com.example.githubapp.utils.ConnectionState
import dagger.Module
import dagger.Provides
import javax.inject.Named
import javax.inject.Singleton

const val EVENT_BUS_USER = "user"
const val EVENT_BUS_REPO = "repo"

@Module
class UtilsModule(private val context: Context) {
    @Provides
    @Singleton
    fun provideConnectionState(): ConnectionState = ConnectionState(context)

    @Provides
    @Singleton
    @Named(EVENT_BUS_USER)
    fun provideUsersEventBus(): EventBus<UserClickEvent> = EventBus()

    @Provides
    @Singleton
    @Named(EVENT_BUS_REPO)
    fun provideReposEventBus(): EventBus<RepoClickEvent> = EventBus()
}