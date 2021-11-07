package com.example.githubapp.di

import com.example.githubapp.domain.GitHubRepo
import com.example.githubapp.ui.repos_list.ReposListContract
import com.example.githubapp.ui.repos_list.ReposListPresenter
import com.example.githubapp.ui.users_list.UsersListContract
import com.example.githubapp.ui.users_list.UsersListPresenter
import com.github.terrakok.cicerone.Router
import dagger.Module
import dagger.Provides

@Module
class PresenterModule {
    @Provides
    fun provideReposListPresenter(
        gitHubRepo: GitHubRepo,
        router: Router
    ): ReposListContract.Presenter {
        return ReposListPresenter(gitHubRepo, router)
    }

    @Provides
    fun provideUsersListPresenter(
        gitHubRepo: GitHubRepo,
        router: Router
    ): UsersListContract.Presenter {
        return UsersListPresenter(gitHubRepo, router)
    }
}