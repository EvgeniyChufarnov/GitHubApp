package com.example.githubapp.event_bus.analytic

import com.example.githubapp.event_bus.EventBus
import io.reactivex.rxjava3.schedulers.Schedulers

class AnalyticObserver(
    private val userClickedEventBus: EventBus<UserClickEvent>,
    private val repoClickedEventBus: EventBus<RepoClickEvent>,
    private val analyticsApi: AnalyticsApi
) {
    init {
        subscribeToUserClicksEvents()
        subscribeToRepoClicksEvents()
    }

    private fun subscribeToUserClicksEvents() {
        userClickedEventBus.get()
            .observeOn(Schedulers.io())
            .subscribe{
                analyticsApi.onUserClicked(it.userLogin)
            }
    }

    private fun subscribeToRepoClicksEvents() {
        repoClickedEventBus.get()
            .observeOn(Schedulers.io())
            .subscribe{
                analyticsApi.onRepoClicked(it.repo)
            }
    }
}