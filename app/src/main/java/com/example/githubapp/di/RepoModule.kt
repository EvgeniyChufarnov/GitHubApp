package com.example.githubapp.di

import com.example.githubapp.data.GitHubCombinedRepo
import com.example.githubapp.data.local.GitHubLocalRepoImpl
import com.example.githubapp.data.remote.impls.GitHubRemoteRepo
import com.example.githubapp.domain.GitHubRepo
import org.koin.dsl.module

val repoModule = module {
    single<GitHubRepo> {
        GitHubCombinedRepo(
            GitHubRemoteRepo(get()),
            GitHubLocalRepoImpl(get(), get()),
            get()
        )
    }
}