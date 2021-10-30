package com.example.githubapp.ui.usersList

import android.os.Bundle
import android.view.View
import androidx.core.view.children
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.githubapp.R
import com.example.githubapp.databinding.FragmentEntitiesListBinding
import com.example.githubapp.event_bus.analytic.UserClickEvent
import com.example.githubapp.utils.app
import com.example.githubapp.domain.entities.UserEntity
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter

class UsersListFragment : MvpAppCompatFragment(R.layout.fragment_entities_list),
    UsersListContract.View {

    private val binding: FragmentEntitiesListBinding by viewBinding(FragmentEntitiesListBinding::bind)
    private val presenter by moxyPresenter {
        UsersListPresenter(app.repoContainer.gitHubRepo, app.router)
    }
    private lateinit var adapter: UsersAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initRecyclerView()
    }

    private fun initRecyclerView() {
        adapter = UsersAdapter(this::onUserClicked)
        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
    }

    private fun onUserClicked(user: UserEntity) {
        app.userClicksEventBus.postValue(UserClickEvent(user.login))
        presenter.onUserClicked(user)
    }

    override fun setUsers(users: List<UserEntity>) {
        adapter.updateData(users)
    }

    override fun setState(state: UsersListContract.ScreenState) {
        binding.root.children.forEach { it.isVisible = false }

        when (state) {
            UsersListContract.ScreenState.IDLE -> binding.recyclerView.isVisible = true
            UsersListContract.ScreenState.LOADING -> binding.processBar.isVisible = true
            UsersListContract.ScreenState.ERROR -> binding.errorTextView.isVisible = true
        }
    }
}