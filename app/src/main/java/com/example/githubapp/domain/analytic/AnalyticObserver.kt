package com.example.githubapp.domain.analytic

import com.example.githubapp.domain.EventBus
import io.reactivex.rxjava3.schedulers.Schedulers

class AnalyticObserver(
    private val userClicksEventBus: EventBus<UserClickEvent>,
    private val analyticsApi: AnalyticsApi
) {
    init {
        subscribeToUserClicksEvents()
    }

    private fun subscribeToUserClicksEvents() {
        userClicksEventBus.get()
            .observeOn(Schedulers.io())
            .subscribe{
                analyticsApi.onUserClicked(it.userLogin)
            }
    }
}