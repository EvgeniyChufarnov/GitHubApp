package com.example.githubapp.domain

import com.example.githubapp.domain.entities.UserEntity
import io.reactivex.rxjava3.core.Single

interface UsersGitHubRepo {
    val users: Single<List<UserEntity>>
}