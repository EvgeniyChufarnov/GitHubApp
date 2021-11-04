package com.example.githubapp.domain

import com.example.githubapp.domain.entities.RepoEntity
import com.example.githubapp.domain.entities.UserEntity

interface GitHubLocalRepo : GitHubRepo {
    fun addUsers(users: List<UserEntity>)
    fun addRepos(owner: String, repos: List<RepoEntity>)
}