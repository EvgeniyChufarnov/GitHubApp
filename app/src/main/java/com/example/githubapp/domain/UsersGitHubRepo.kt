package com.example.githubapp.domain

import com.example.githubapp.domain.entities.UserEntity

interface UsersGitHubRepo {
    suspend fun getUsers(): List<UserEntity>
}