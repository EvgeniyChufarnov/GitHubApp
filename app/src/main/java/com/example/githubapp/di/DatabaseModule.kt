package com.example.githubapp.di

import android.content.Context
import androidx.room.Room
import com.example.githubapp.data.local.ReposDao
import com.example.githubapp.data.local.ReposDb
import com.example.githubapp.data.local.UsersDao
import com.example.githubapp.data.local.UsersDb
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

private const val USERS_DB_PATH = "users.db"
private const val REPOS_DB_PATH = "repos.db"

@Module
class DatabaseModule(private val context: Context) {
    @Provides
    @Singleton
    fun provideUsersDao(): UsersDao {
        return Room.databaseBuilder(
            context,
            UsersDb::class.java,
            USERS_DB_PATH
        )
            .build()
            .usersDao()
    }

    @Provides
    @Singleton
    fun provideReposDao(): ReposDao {
        return Room.databaseBuilder(
            context,
            ReposDb::class.java,
            REPOS_DB_PATH
        )
            .build()
            .reposDao()
    }
}