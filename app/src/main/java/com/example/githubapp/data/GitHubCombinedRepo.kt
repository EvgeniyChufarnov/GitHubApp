package com.example.githubapp.data

import com.example.githubapp.utils.ConnectionState
import com.example.githubapp.data.remote.impls.GitHubRemoteRepo
import com.example.githubapp.domain.GitHubLocalRepo
import com.example.githubapp.domain.GitHubRepo
import com.example.githubapp.domain.entities.RepoEntity
import com.example.githubapp.domain.entities.UserEntity
import io.reactivex.rxjava3.core.Single
import retrofit2.Response

class GitHubCombinedRepo(
    private val gitHubRemoteRepo: GitHubRemoteRepo,
    private val gitHubLocalRepo: GitHubLocalRepo,
    private val connectionState: ConnectionState
) : GitHubRepo {
    override fun getRepos(name: String): Single<Response<List<RepoEntity>>> {
        return if (connectionState.isAvailable) {
            gitHubRemoteRepo.getRepos(name).doOnSuccessfulResult {
                gitHubLocalRepo.addRepos(name, it)
            }
        } else {
            gitHubLocalRepo.getRepos(name)
        }
    }

    override fun getUsers(): Single<Response<List<UserEntity>>> = if (connectionState.isAvailable) {
        gitHubRemoteRepo.getUsers().doOnSuccessfulResult {
            gitHubLocalRepo.addUsers(it)
        }
    } else {
        gitHubLocalRepo.getUsers()
    }
}

private fun <T : Any> Single<Response<T>>.doOnSuccessfulResult(
    action: (T) -> Unit
): Single<Response<T>> {
    subscribe { result ->
        if (result.isSuccessful) {
            result.body()?.let { action(it) }
        }
    }

    return this
}


