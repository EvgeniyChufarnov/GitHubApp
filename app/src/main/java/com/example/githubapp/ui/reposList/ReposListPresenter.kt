package com.example.githubapp.ui.reposList

import com.example.githubapp.domain.GitHubRepo
import com.example.githubapp.domain.entities.RepoEntity
import com.example.githubapp.ui.common.Screens.RepoScreen
import com.github.terrakok.cicerone.Router
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable

class ReposListPresenter(
    private val repo: GitHubRepo,
    private val router: Router
) : ReposListContract.Presenter() {

    private var compositeDisposable = CompositeDisposable()

    override fun onUsernameExtracted(username: String) {
        getRepos(username)
    }

    private fun getRepos(username: String) {
        viewState.setState(ReposListContract.ScreenState.LOADING)

        compositeDisposable.add(
            repo.getRepos(username)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ response ->
                    if (response.isSuccessful) {
                        viewState.setRepos(response.body() ?: emptyList())
                        viewState.setState(ReposListContract.ScreenState.IDLE)
                    } else {
                        viewState.setState(ReposListContract.ScreenState.ERROR)
                    }
                }, {
                    viewState.setState(ReposListContract.ScreenState.ERROR)
                })
        )
    }

    override fun onRepoClicked(repo: RepoEntity) {
        router.navigateTo(RepoScreen(repo))
    }

    override fun onDestroy() {
        compositeDisposable.dispose()
        super.onDestroy()
    }
}