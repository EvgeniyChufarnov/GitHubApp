package com.example.githubapp.ui.repos_list

import com.example.githubapp.domain.entities.RepoEntity
import moxy.MvpPresenter
import moxy.MvpView
import moxy.viewstate.strategy.alias.AddToEndSingle

class ReposListContract {
    enum class ScreenState {
        IDLE, LOADING, ERROR
    }

    interface View : MvpView {
        @AddToEndSingle
        fun setRepos(repos: List<RepoEntity>)

        @AddToEndSingle
        fun setState(state: ScreenState)
    }

    abstract class Presenter : MvpPresenter<View>() {
        abstract fun onUsernameExtracted(username: String)
        abstract fun onRepoClicked(repo: RepoEntity)
    }
}