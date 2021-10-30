package com.example.githubapp.di

import com.example.githubapp.data.remote.GitHubApi
import com.example.githubapp.data.remote.impls.GitHubRepoImpl
import com.example.githubapp.domain.GitHubRepo
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

const val GITHUB_URL = "https://api.github.com/"

class RepoContainer {
    private val retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
        .baseUrl(GITHUB_URL)
        .build()

    private val gitHubApi = retrofit.create(GitHubApi::class.java)

    val gitHubRepo: GitHubRepo = GitHubRepoImpl(gitHubApi)
}