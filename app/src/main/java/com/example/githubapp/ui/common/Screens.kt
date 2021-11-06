package com.example.githubapp.ui.common

import com.example.githubapp.domain.entities.RepoEntity
import com.example.githubapp.ui.repo.RepoFragment
import com.example.githubapp.ui.repos_list.ReposListFragment
import com.example.githubapp.ui.users_list.UsersListFragment
import com.github.terrakok.cicerone.androidx.FragmentScreen

object Screens {
    fun UsersListScreen() = FragmentScreen { UsersListFragment() }

    fun ReposListScreen(username: String) = FragmentScreen {
        ReposListFragment.getInstance(username)
    }

    fun RepoScreen(repo: RepoEntity) = FragmentScreen {
        RepoFragment.getInstance(repo)
    }
}