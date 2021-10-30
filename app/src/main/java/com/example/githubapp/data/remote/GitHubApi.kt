package com.example.githubapp.data.remote

import com.example.githubapp.domain.entities.RepoEntity
import com.example.githubapp.domain.entities.UserEntity
import io.reactivex.rxjava3.core.Single
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface GitHubApi {
    @GET("users/{name}/repos")
    fun getRepos(
        @Path("name") name: String,
    ): Single<Response<List<RepoEntity>>>

    @GET("users")
    fun getUsers(): Single<Response<List<UserEntity>>>
}