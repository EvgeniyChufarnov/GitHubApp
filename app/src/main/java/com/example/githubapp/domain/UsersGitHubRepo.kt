package com.example.githubapp.domain

import com.example.githubapp.domain.entities.UserEntity
import io.reactivex.rxjava3.core.Observable

interface UsersGitHubRepo {
    val users: Observable<List<UserEntity>>
}