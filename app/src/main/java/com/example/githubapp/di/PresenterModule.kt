package com.example.githubapp.di

import com.example.githubapp.ui.repos_list.ReposListContract
import com.example.githubapp.ui.repos_list.ReposListPresenter
import com.example.githubapp.ui.users_list.UsersListContract
import com.example.githubapp.ui.users_list.UsersListPresenter
import org.koin.dsl.module

val presenterModule = module {
    factory<ReposListContract.Presenter>{
        ReposListPresenter(get(), get())
    }

    factory<UsersListContract.Presenter>{
        UsersListPresenter(get(), get())
    }
}