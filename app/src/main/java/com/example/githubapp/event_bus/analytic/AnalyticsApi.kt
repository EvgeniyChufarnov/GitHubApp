package com.example.githubapp.event_bus.analytic

interface AnalyticsApi {
    fun onUserClicked(userName: String)
    fun onRepoClicked(repo: String)
}