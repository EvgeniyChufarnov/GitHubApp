package com.example.githubapp.di

import androidx.room.Room
import com.example.githubapp.data.local.ReposDb
import com.example.githubapp.data.local.UsersDb
import org.koin.dsl.module

private const val USERS_DB_PATH = "users.db"
private const val REPOS_DB_PATH = "repos.db"

val databaseModule = module {
    single {
        Room.databaseBuilder(
            get(),
            UsersDb::class.java,
            USERS_DB_PATH
        ).build()
    }

    single { get<UsersDb>().usersDao() }

    single {
        Room.databaseBuilder(
            get(),
            ReposDb::class.java,
            REPOS_DB_PATH
        ).build()
    }

    single { get<ReposDb>().reposDao() }
}