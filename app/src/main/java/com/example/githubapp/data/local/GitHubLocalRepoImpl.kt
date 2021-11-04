package com.example.githubapp.data.local

import androidx.annotation.WorkerThread
import com.example.githubapp.data.local.entities.CachedRepos
import com.example.githubapp.data.local.entities.ReposEntity
import com.example.githubapp.domain.GitHubLocalRepo
import com.example.githubapp.domain.entities.RepoEntity
import com.example.githubapp.domain.entities.UserEntity
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import retrofit2.Response

class GitHubLocalRepoImpl(
    private val usersDao: UsersDao,
    private val reposDao: ReposDao,
): GitHubLocalRepo {
    @WorkerThread
    override fun getUsers(): Single<Response<List<UserEntity>>> {
        return Single.fromCallable { Response.success(usersDao.getUsers()) }
            .subscribeOn(Schedulers.io())
    }

    @WorkerThread
    override fun addUsers(users: List<UserEntity>) {
        usersDao.clear()
        usersDao.cacheUsers(users)
    }

    @WorkerThread
    override fun getRepos(name: String): Single<Response<List<RepoEntity>>> {
        return Single.fromCallable { Response.success(reposDao.getRepos(name).cachedRepos.value) }
            .subscribeOn(Schedulers.io())
    }

    @WorkerThread
    override fun addRepos(owner: String, repos: List<RepoEntity>) {
        reposDao.cacheRepos(ReposEntity(owner, CachedRepos(repos)))
    }
}