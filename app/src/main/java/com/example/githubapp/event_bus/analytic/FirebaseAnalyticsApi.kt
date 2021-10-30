package com.example.githubapp.event_bus.analytic

import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.analytics.ktx.analytics
import com.google.firebase.analytics.ktx.logEvent
import com.google.firebase.ktx.Firebase

private const val USER_CLICKED_EVENT_KEY = "user_clicked"
private const val USER_NAME_PARAM_KEY = "user_name"
private const val REPO_CLICKED_EVENT_KEY = "repo_clicked"
private const val REPO_NAME_PARAM_KEY = "repo_name"

class FirebaseAnalyticsApi : AnalyticsApi {
    private val analytics: FirebaseAnalytics by lazy {
        Firebase.analytics
    }

    override fun onUserClicked(userName: String) {
        analytics.logEvent(USER_CLICKED_EVENT_KEY) {
            param(USER_NAME_PARAM_KEY, userName)
        }
    }

    override fun onRepoClicked(repo: String) {
        analytics.logEvent(REPO_CLICKED_EVENT_KEY) {
            param(REPO_NAME_PARAM_KEY, repo)
        }
    }
}