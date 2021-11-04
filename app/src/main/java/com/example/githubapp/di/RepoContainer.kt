package com.example.githubapp.di

import android.content.Context
import androidx.room.Room
import com.example.githubapp.utils.ConnectionState
import com.example.githubapp.data.GitHubCombinedRepo
import com.example.githubapp.data.local.GitHubLocalRepoImpl
import com.example.githubapp.data.local.ReposDb
import com.example.githubapp.data.local.UsersDb
import com.example.githubapp.data.remote.GitHubApi
import com.example.githubapp.data.remote.impls.GitHubRemoteRepo
import com.example.githubapp.domain.GitHubRepo
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

private const val GITHUB_URL = "https://api.github.com/"
private const val USERS_DB_PATH = "users.db"
private const val REPOS_DB_PATH = "repos.db"

class RepoContainer(context: Context) {
    private val retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
        .baseUrl(GITHUB_URL)
        .build()

    private val gitHubApi = retrofit.create(GitHubApi::class.java)

    private val usersDb = Room.databaseBuilder(
        context,
        UsersDb::class.java,
        USERS_DB_PATH
    ).build()

    private val usersDao = usersDb.usersDao()

    private val reposDb = Room.databaseBuilder(
        context,
        ReposDb::class.java,
        REPOS_DB_PATH
    ).build()

    private val reposDao = reposDb.reposDao()

    private val connectionState = ConnectionState(context)

    val gitHubRepo: GitHubRepo = GitHubCombinedRepo(
        GitHubRemoteRepo(gitHubApi),
        GitHubLocalRepoImpl(usersDao, reposDao),
        connectionState
    )
}