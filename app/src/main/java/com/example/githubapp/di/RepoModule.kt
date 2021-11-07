package com.example.githubapp.di

import com.example.githubapp.data.GitHubCombinedRepo
import com.example.githubapp.data.local.GitHubLocalRepoImpl
import com.example.githubapp.data.local.ReposDao
import com.example.githubapp.data.local.UsersDao
import com.example.githubapp.data.remote.GitHubApi
import com.example.githubapp.data.remote.impls.GitHubRemoteRepo
import com.example.githubapp.domain.GitHubRepo
import com.example.githubapp.utils.ConnectionState
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepoModule {
    @Provides
    @Singleton
    fun providesGitHubRepo(
        gitHubApi: GitHubApi,
        usersDao: UsersDao,
        reposDao: ReposDao,
        connectionState: ConnectionState
    ): GitHubRepo {
        return GitHubCombinedRepo(
            GitHubRemoteRepo(gitHubApi),
            GitHubLocalRepoImpl(usersDao, reposDao),
            connectionState
        )
    }
}