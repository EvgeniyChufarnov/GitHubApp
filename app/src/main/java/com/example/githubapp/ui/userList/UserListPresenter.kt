package com.example.githubapp.ui.userList

import com.example.githubapp.domain.UsersGitHubRepo
import com.example.githubapp.domain.entities.UserEntity
import com.example.githubapp.ui.common.Screens.UserScreen
import com.github.terrakok.cicerone.Router
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable

class UserListPresenter(
    private val repo: UsersGitHubRepo,
    private val router: Router
) : Contract.Presenter() {

    private var compositeDisposable = CompositeDisposable()

    override fun onFirstViewAttach() {
        getUsers()
    }

    private fun getUsers() {
        viewState.setState(Contract.ScreenState.LOADING)

        compositeDisposable.add(
            repo.users
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    viewState.setUsers(it)
                    viewState.setState(Contract.ScreenState.IDLE)
                }, {
                    viewState.setState(Contract.ScreenState.ERROR)
                })
        )
    }

    override fun onUserClicked(user: UserEntity) {
        router.navigateTo(UserScreen(user.login))
    }

    override fun onDestroy() {
        compositeDisposable.dispose()
        super.onDestroy()
    }
}