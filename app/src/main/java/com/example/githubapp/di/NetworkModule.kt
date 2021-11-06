package com.example.githubapp.di

import com.example.githubapp.data.remote.GitHubApi
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

private const val GITHUB_URL = "https://api.github.com/"

val networkModule = module {
    single {
        Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .baseUrl(GITHUB_URL)
            .build()
    }

    single { get<Retrofit>().create(GitHubApi::class.java) }
}