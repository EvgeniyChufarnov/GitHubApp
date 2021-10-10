package com.example.githubapp.di

import com.example.githubapp.domain.UsersGitHubRepo
import com.example.githubapp.domain.impls.FakeUsersGitHubRepoImpl

class RepoContainer {
    val usersGitHubRepo: UsersGitHubRepo = FakeUsersGitHubRepoImpl()
}