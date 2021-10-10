package com.example.githubapp.domain.impls

import com.example.githubapp.domain.UsersGitHubRepo
import com.example.githubapp.domain.entities.UserEntity
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext

private const val LOADING_DELAY = 3000L

class FakeUsersGitHubRepoImpl(
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) : UsersGitHubRepo {

    override suspend fun getUsers(): List<UserEntity> = withContext(dispatcher) {
        delay(LOADING_DELAY)

        listOf(
            UserEntity("User1"),
            UserEntity("User2"),
            UserEntity("User3"),
            UserEntity("User4"),
            UserEntity("User5"),
            UserEntity("User6"),
            UserEntity("User7"),
            UserEntity("User8"),
            UserEntity("User9"),
            UserEntity("User10")
        )
    }
}