package com.example.githubapp.ui.common

import com.example.githubapp.ui.user.UserFragment
import com.example.githubapp.ui.userList.UserListFragment
import com.github.terrakok.cicerone.androidx.FragmentScreen

object Screens {
    fun UserListScreen() = FragmentScreen { UserListFragment() }
    fun UserScreen(login: String) = FragmentScreen { UserFragment.getInstance(login) }
}