package com.example.githubapp.domain.impls

import com.example.githubapp.domain.UsersGitHubRepo
import com.example.githubapp.domain.entities.UserEntity
import io.reactivex.rxjava3.annotations.NonNull
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.subjects.BehaviorSubject
import java.util.concurrent.TimeUnit

private const val LOADING_DELAY = 3000L

class FakeUsersGitHubRepoImpl : UsersGitHubRepo {
    private val fakeUsers: List<UserEntity> = listOf(
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

    override val users: Observable<List<UserEntity>> = BehaviorSubject.createDefault(fakeUsers)
        get() = field.delay(LOADING_DELAY, TimeUnit.MILLISECONDS)
}