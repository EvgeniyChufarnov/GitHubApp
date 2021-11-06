package com.example.githubapp.ui.users_list

import com.example.githubapp.domain.GitHubRepo
import com.example.githubapp.domain.entities.UserEntity
import com.example.githubapp.ui.common.Screens.ReposListScreen
import com.github.terrakok.cicerone.Router
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable

class UsersListPresenter(
    private val repo: GitHubRepo,
    private val router: Router
) : UsersListContract.Presenter() {

    private var compositeDisposable = CompositeDisposable()

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()

        getUsers()
    }

    private fun getUsers() {
        viewState.setState(UsersListContract.ScreenState.LOADING)

        compositeDisposable.add(
            repo.getUsers()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ response ->
                    if (response.isSuccessful) {
                        viewState.setUsers(response.body() ?: emptyList())
                        viewState.setState(UsersListContract.ScreenState.IDLE)
                    } else {
                        viewState.setState(UsersListContract.ScreenState.ERROR)
                    }
                }, {
                    viewState.setState(UsersListContract.ScreenState.ERROR)
                })
        )
    }

    override fun onUserClicked(user: UserEntity) {
        router.navigateTo(ReposListScreen(user.login))
    }

    override fun onDestroy() {
        compositeDisposable.dispose()
        super.onDestroy()
    }
}