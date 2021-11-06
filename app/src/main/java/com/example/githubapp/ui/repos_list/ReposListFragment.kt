package com.example.githubapp.ui.repos_list

import android.os.Bundle
import android.view.View
import androidx.core.view.children
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.githubapp.R
import com.example.githubapp.databinding.FragmentEntitiesListBinding
import com.example.githubapp.di.EVENT_BUS_REPO
import com.example.githubapp.domain.entities.RepoEntity
import com.example.githubapp.event_bus.EventBus
import com.example.githubapp.event_bus.analytic.RepoClickEvent
import moxy.MvpAppCompatFragment
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter
import org.koin.android.ext.android.get
import org.koin.android.ext.android.inject
import org.koin.core.qualifier.named

class ReposListFragment : MvpAppCompatFragment(R.layout.fragment_entities_list),
    ReposListContract.View {

    @InjectPresenter
    lateinit var presenter: ReposListContract.Presenter

    @ProvidePresenter
    fun provide(): ReposListContract.Presenter = get()

    private val binding: FragmentEntitiesListBinding by viewBinding(FragmentEntitiesListBinding::bind)
    private val repoClickedEventBus: EventBus<RepoClickEvent> by inject(named(EVENT_BUS_REPO))

    private lateinit var adapter: ReposAdapter

    companion object {
        private const val USERNAME_KEY = "username"

        fun getInstance(username: String) = ReposListFragment().apply {
            arguments = Bundle().apply {
                putString(USERNAME_KEY, username)
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initRecyclerView()

        arguments?.getString(USERNAME_KEY)?.let {
            presenter.onUsernameExtracted(it)
        }
    }

    private fun initRecyclerView() {
        adapter = ReposAdapter(this::onRepoClicked)
        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
    }

    private fun onRepoClicked(repo: RepoEntity) {
        repoClickedEventBus.postValue(RepoClickEvent(repo.name))
        presenter.onRepoClicked(repo)
    }

    override fun setRepos(repos: List<RepoEntity>) {
        adapter.updateData(repos)
    }

    override fun setState(state: ReposListContract.ScreenState) {
        binding.root.children.forEach { it.isVisible = false }

        when (state) {
            ReposListContract.ScreenState.IDLE -> binding.recyclerView.isVisible = true
            ReposListContract.ScreenState.LOADING -> binding.processBar.isVisible = true
            ReposListContract.ScreenState.ERROR -> binding.errorTextView.isVisible = true
        }
    }
}