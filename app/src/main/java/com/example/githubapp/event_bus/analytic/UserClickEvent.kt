package com.example.githubapp.event_bus.analytic

import com.example.githubapp.event_bus.EventBus

data class UserClickEvent(
    val userLogin: String
) : EventBus.Event()