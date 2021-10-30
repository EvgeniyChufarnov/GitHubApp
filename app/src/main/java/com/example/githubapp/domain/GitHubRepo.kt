package com.example.githubapp.domain

import com.example.githubapp.domain.entities.RepoEntity
import com.example.githubapp.domain.entities.UserEntity
import io.reactivex.rxjava3.core.Single
import retrofit2.Response

interface GitHubRepo {
    fun getRepos(name: String): Single<Response<List<RepoEntity>>>
    fun getUsers(): Single<Response<List<UserEntity>>>
}