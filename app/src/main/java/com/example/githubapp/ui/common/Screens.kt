package com.example.githubapp.ui.common

import com.example.githubapp.domain.entities.RepoEntity
import com.example.githubapp.ui.repo.RepoFragment
import com.example.githubapp.ui.reposList.ReposListFragment
import com.example.githubapp.ui.usersList.UsersListFragment
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