package com.example.githubapp.ui.repos_list

import com.example.githubapp.domain.GitHubRepo
import com.example.githubapp.domain.entities.RepoEntity
import com.example.githubapp.ui.common.Screens.RepoScreen
import com.github.terrakok.cicerone.Router
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.disposables.CompositeDisposable
import java.util.concurrent.TimeUnit
import java.util.concurrent.atomic.AtomicBoolean

private const val LOADING_BAR_DELAY = 300L

class ReposListPresenter(
    private val repo: GitHubRepo,
    private val router: Router
) : ReposListContract.Presenter() {

    private var compositeDisposable = CompositeDisposable()
    private var isRequestFinished: AtomicBoolean = AtomicBoolean(false)

    override fun onUsernameExtracted(username: String) {
        if (!isRequestFinished.get()) {
            getRepos(username)
        }
    }

    private fun getRepos(username: String) {
        compositeDisposable.add(
            repo.getRepos(username)
                .doOnSubscribe { delayLoadingBar() }
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ response ->
                    if (response.isSuccessful) {
                        onRequestSuccess(response.body() ?: emptyList())
                    } else {
                        onRequestError()
                    }
                }, {
                    onRequestError()
                })
        )
    }

    private fun delayLoadingBar() {
        compositeDisposable.add(
            Completable.timer(LOADING_BAR_DELAY, TimeUnit.MILLISECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    if (!isRequestFinished.get()) {
                        viewState.setState(ReposListContract.ScreenState.LOADING)
                    }
                }
        )
    }

    private fun onRequestSuccess(repos: List<RepoEntity>) {
        isRequestFinished.set(true)
        viewState.setRepos(repos)
        viewState.setState(ReposListContract.ScreenState.IDLE)
    }

    private fun onRequestError() {
        isRequestFinished.set(true)
        viewState.setState(ReposListContract.ScreenState.ERROR)
    }

    override fun onRepoClicked(repo: RepoEntity) {
        router.navigateTo(RepoScreen(repo))
    }

    override fun onDestroy() {
        compositeDisposable.dispose()
        super.onDestroy()
    }
}