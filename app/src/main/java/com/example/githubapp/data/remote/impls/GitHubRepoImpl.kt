package com.example.githubapp.data.remote.impls

import com.example.githubapp.data.remote.GitHubApi
import com.example.githubapp.domain.GitHubRepo
import com.example.githubapp.domain.entities.RepoEntity
import com.example.githubapp.domain.entities.UserEntity
import io.reactivex.rxjava3.core.Single
import retrofit2.Response

class GitHubRepoImpl(
    private val gitHubApi: GitHubApi
) : GitHubRepo {
    override fun getRepos(name: String): Single<Response<List<RepoEntity>>> {
        return gitHubApi.getRepos(name)
    }

    override fun getUsers(): Single<Response<List<UserEntity>>> {
        return gitHubApi.getUsers()
    }
}