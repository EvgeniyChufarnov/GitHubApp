package com.example.githubapp.ui.userList

import com.example.githubapp.domain.UsersGitHubRepo
import com.example.githubapp.domain.entities.UserEntity
import com.example.githubapp.ui.common.Screens.UserScreen
import com.github.terrakok.cicerone.Router
import kotlinx.coroutines.launch
import moxy.presenterScope

class UserListPresenter(
    private val repo: UsersGitHubRepo,
    private val router: Router
) : Contract.Presenter() {

    override fun onFirstViewAttach() {
        getUsers()
    }

    private fun getUsers() {
        viewState.setState(Contract.ScreenState.LOADING)

        try {
            presenterScope.launch {
                viewState.setUsers(repo.getUsers())
                viewState.setState(Contract.ScreenState.IDLE)
            }
        } catch (throwable: Throwable) {
            viewState.setState(Contract.ScreenState.ERROR)
        }
    }

    override fun onUserClicked(user: UserEntity) {
        router.navigateTo(UserScreen(user.login))
    }
}