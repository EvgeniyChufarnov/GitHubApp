package com.example.githubapp.event_bus.analytic

import com.example.githubapp.event_bus.EventBus

data class RepoClickEvent(
    val repo: String
) : EventBus.Event()