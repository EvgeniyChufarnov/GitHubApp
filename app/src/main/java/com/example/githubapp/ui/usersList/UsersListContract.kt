package com.example.githubapp.ui.usersList

import com.example.githubapp.domain.entities.UserEntity
import moxy.MvpPresenter
import moxy.MvpView
import moxy.viewstate.strategy.alias.AddToEndSingle

class UsersListContract {
    enum class ScreenState {
        IDLE, LOADING, ERROR
    }

    interface View : MvpView {
        @AddToEndSingle
        fun setUsers(users: List<UserEntity>)

        @AddToEndSingle
        fun setState(state: ScreenState)
    }

    abstract class Presenter : MvpPresenter<View>() {
        abstract fun onUserClicked(user: UserEntity)
    }
}