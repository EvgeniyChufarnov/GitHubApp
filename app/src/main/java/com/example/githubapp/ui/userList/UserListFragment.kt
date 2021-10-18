package com.example.githubapp.ui.userList

import android.os.Bundle
import android.view.View
import androidx.core.view.children
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.githubapp.R
import com.example.githubapp.databinding.FragmentUsersListBinding
import com.example.githubapp.domain.app
import com.example.githubapp.domain.entities.UserEntity
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter

class UserListFragment : MvpAppCompatFragment(R.layout.fragment_users_list), Contract.View {

    private val binding: FragmentUsersListBinding by viewBinding(FragmentUsersListBinding::bind)
    private val presenter by moxyPresenter {
        UserListPresenter(app.repoContainer.usersGitHubRepo, app.router)
    }
    private lateinit var adapter: UsersAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initRecyclerView()
    }

    private fun initRecyclerView() {
        adapter = UsersAdapter(presenter::onUserClicked)
        binding.usersRecyclerView.adapter = adapter
        binding.usersRecyclerView.layoutManager = LinearLayoutManager(requireContext())
    }

    override fun setUsers(users: List<UserEntity>) {
        adapter.updateData(users)
    }

    override fun setState(state: Contract.ScreenState) {
        binding.root.children.forEach { it.isVisible = false }

        when (state) {
            Contract.ScreenState.IDLE -> binding.usersRecyclerView.isVisible = true
            Contract.ScreenState.LOADING -> binding.processBar.isVisible = true
            Contract.ScreenState.ERROR -> binding.errorTextView.isVisible = true
        }
    }
}